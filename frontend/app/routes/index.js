import Ember from 'ember';
import AuthenticatedRoute from './authenticated-route';

export default AuthenticatedRoute.extend({	
    miscService: Ember.inject.service(),

	model: function() {
		return Ember.RSVP.hash({
			stats: this.get('miscService').stats()
		});
	},

});
