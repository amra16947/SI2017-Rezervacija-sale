import Ember from 'ember';
import Equipment from '../models/equipment';

export default Ember.Route.extend({
	classroomService: Ember.inject.service(),
	equipmentTypeService: Ember.inject.service(),

	model: function(params, transition) {
        return Ember.RSVP.hash({
            classroom: this.get('classroomService').getById(params.id),
            equipment: Equipment.create({ quantity: "", classroomId: params.id, equipmentTypeId: 1 }),
            types: this.get('equipmentTypeService').all()
        });
    }
});
