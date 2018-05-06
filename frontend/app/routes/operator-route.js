import Ember from 'ember';
import BaseRoute from 'restaurants-app/routes/base-route';

// Limits access to this route only to OPERATOR users
export default BaseRoute.extend({
	beforeModel: function(transition) {
		if(this.get('currentUser.role') !== 'ROLE_OPERATOR') {
			return this.transitionTo("index");
		}
	}
});