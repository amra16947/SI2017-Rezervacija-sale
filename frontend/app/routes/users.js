import Ember from 'ember';
import AuthenticatedRoute from './authenticated-route';
import User from '../models/user';

export default AuthenticatedRoute.extend({
  access: ['ROLE_OPERATOR', 'ROLE_ADMIN'],

  userService: Ember.inject.service(),

  beforeModel: function(transition) {
    if (this.get('access').contains(this.get('currentUser.role'))) {
      return true;
    }
    else this.transitionTo('index');
  },

	model: function() {
		return Ember.RSVP.hash({
			users: this.get('userService').all(),
			user: User.create({username: "", password: "", firstName: "", lastName: "", type: 0}),
		});
	},

	setupController: function(controller, model) {
	    this._super(controller, model);
	    controller.set('filterTerm', '');
  	}
});
