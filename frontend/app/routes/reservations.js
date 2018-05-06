import Ember from 'ember';
import Reservation from '../models/reservation';

export default Ember.Route.extend({
	reservationService: Ember.inject.service(),
  userService: Ember.inject.service(),
  classroomService: Ember.inject.service(),

	access: ['ROLE_OPERATOR'],

	beforeModel: function(transition) {
		if (this.get('access').contains(this.get('currentUser.role'))) {
			return true;
		}
		else this.transitionTo('index');
	},

	model: function(params, transition) {
        return Ember.RSVP.hash({
            reservations: this.get('reservationService').findByClassroom(params.id),
            classroom: this.get('classroomService').getById(params.id),
            reservation: Reservation.create({ teacherId: "", reservedAt: "", duration: 1, classroomId: params.id}),
			users: this.get('userService').all()
        });
    },

    setupController: function(controller, model) {
        this._super(controller, model);
        controller.set('niceTime', '');
    }
});
