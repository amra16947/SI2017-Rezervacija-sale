import Ember from 'ember';
import Access from '../models/access';

export default Ember.Route.extend({
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
            users: this.get('userService').all(),
            classroom: this.get('classroomService').getById(params.id),
            access: Access.create({ userId: "", classroomId: params.id }),
        });
    },
});
