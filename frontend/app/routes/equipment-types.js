import Ember from 'ember';
import Equipment from '../models/equipment';

export default Ember.Route.extend({
	equipmentTypeService: Ember.inject.service(),
	access: ['ROLE_OPERATOR', 'ROLE_ADMIN'],

	beforeModel: function(transition) {
		if (this.get('access').contains(this.get('currentUser.role'))) {
			return true;
		}
		else this.transitionTo('index');
	},

  model: function() {
    return Ember.RSVP.hash({
      types: this.get('equipmentTypeService').all(),
      equipment: Equipment.create({ label: "", name: "" }),
    });
  },
});
