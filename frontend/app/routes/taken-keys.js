import Ember from 'ember';
import Reservation from '../models/reservation';

export default Ember.Route.extend({
	reservationService: Ember.inject.service(),

	access: ['ROLE_OPERATOR'],

	beforeModel: function(transition) {
		if (this.get('access').contains(this.get('currentUser.role'))) {
			return true;
		}
		else this.transitionTo('index');
	},

	model: function(transition) {
        return Ember.RSVP.hash({
            reservations: this.get('reservationService').findAllToday()
        });
    },

    setupController: function(controller, model) {
        this._super(controller, model);
        controller.set('selectedReservation', null);
    }
});
