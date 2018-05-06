import Ember from 'ember';
import Reservation from '../models/reservation';
import moment from 'moment';

export default Ember.Controller.extend({
    showKeyActions: false,
    selectedReservation: null,
    takenKeyService: Ember.inject.service(),
    reservationService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

    reservations: Ember.computed.map('model.reservations', function(reservation, index) {
        var start = reservation.reservedAt;
        var end = moment(start).add(reservation.duration, 'hours').format("YYYY-MM-DDTHH:mm:ss");

    	var classroom = reservation.classroom;
    	var professor = reservation.teacher;

        var event = {
            title: `${classroom.name} (ključevi: ${classroom.takenKeyCount}/${classroom.keyCount}) - ${professor.firstName} ${professor.lastName}`,
            start: start,
            end: end,
            reservation: reservation
        }

        return event;
    }),

    actions: {

        reservationClicked(event, jsEvent, view) {
        	this.set('selectedReservation', event.reservation);
        	this.set('showKeyActions', true);
        },

        toggleKeyActions() {
            this.toggleProperty('showKeyActions');
        },

        takeKey() {
			const flashMessages = Ember.get(this, 'flashMessages');
        	this.get('takenKeyService').takeKey(this.get('selectedReservation.id')).then(function() {
        		this.set('model.reservations', this.get('reservationService').findAllToday());
                flashMessages.success("Ključ izdat.");
        		this.set('showKeyActions', false);
        	}.bind(this), function() {
                flashMessages.danger("Greška pri izdavanju ključa.");
        		this.set('showKeyActions', false);
        	}.bind(this));
        },

        returnKey() {
			const flashMessages = Ember.get(this, 'flashMessages');
        	this.get('takenKeyService').returnKey(this.get('selectedReservation.id')).then(function() {
        		this.set('model.reservations', this.get('reservationService').findAllToday());
                flashMessages.success("Ključ vraćen.");
        		this.set('showKeyActions', false);
        	}.bind(this), function() {
                flashMessages.danger("Greška pri vraćanju ključa.");
        		this.set('showKeyActions', false);
        	}.bind(this));
        }

    }
});
