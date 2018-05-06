import Ember from 'ember';
import Reservation from '../models/reservation';
import moment from 'moment';
import {
    validator,
    buildValidations
}
from 'ember-cp-validations';

var Validations = buildValidations({
    "model.reservation.teacherId": [
        validator('presence', {
            presence: true
        })
    ]
}, {
    debounce: 500
});

export default Ember.Controller.extend(Validations, {
    showNewReservation: false,
    reservationService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),


    reservations: Ember.computed.map('model.reservations', function(reservation, index) {
        var start = reservation.reservedAt;
        var end = moment(start).add(reservation.duration, 'hours').format("YYYY-MM-DDTHH:mm:ss");
        var event = {
            title: `${reservation.teacher.firstName} ${reservation.teacher.lastName}`,
            start: start,
            end: end,
            reservation: reservation
        }

        return event;
    }),

    actions: {
        add() {
            const flashMessages = Ember.get(this, 'flashMessages');
            this.get('reservationService').create(this.get('model.reservation')).then(function(newReservation) {
                this.get('model.reservations').pushObject(Reservation.create(newReservation));

                this.set('model.reservation.teacherId', '');
                this.set('model.reservation.duration', 1);

                flashMessages.success("Rezervacija dodana.");
                this.set('showNewReservation', false);
            }.bind(this), function() {
                flashMessages.danger("Greška pri dodavanju rezervacije (poklapa se sa već nekom rezervacijom).");
                this.set('showNewReservation', false);
            }.bind(this));
        },

        dayClicked(date, jsEvent, view) {
            var clickedDate = moment(date);
            clickedDate.minutes(0);
            var formattedDate = clickedDate.format("YYYY-MM-DD") + "T" + clickedDate.format("HH:mm:ss");
            this.set('model.reservation.reservedAt', formattedDate);
            this.set('niceTime', clickedDate.format("YYYY-MM-DD HH:mm"))
            this.set('showNewReservation', true);
        },

        reservationClicked(event, jsEvent, view) {
            if (window.confirm("Želite li izbrisati ovu rezervaciju?")) {
                const flashMessages = Ember.get(this, 'flashMessages');
                this.get('reservationService').delete(event.reservation.id).then(function() {
                    this.get('model.reservations').removeObject(event.reservation);
                }.bind(this), function(data) {
                    flashMessages.danger("Greška pri brisanju rezervacije.");
                }.bind(this));
            }
        },

        selectUser(user) {
            this.set('model.reservation.teacherId', user);
        },

        toggleNewReservation() {
            this.toggleProperty('showNewReservation');
        },


    }
});
