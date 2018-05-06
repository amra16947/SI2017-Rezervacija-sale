import Ember from 'ember';
import Equipment from '../models/equipment';
import {
    validator,
    buildValidations
}
from 'ember-cp-validations';

var Validations = buildValidations({
    "model.equipment.quantity": [
        validator('number', {
		  allowString: true,
		  integer: true,
		  gt: 0,
		  lte: 1000
		}),
		 validator('presence', {
            presence: true
        })
    ]
}, {
    debounce: 500
});

export default Ember.Controller.extend(Validations, {
    showNewEquipment: false,
    classroomEquipmentService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

	actions: {
		add() {
			const flashMessages = Ember.get(this, 'flashMessages');
            this.get('classroomEquipmentService').create(this.get('model.equipment')).then(function(newEquipment) {
                this.get('model.classroom.equipment').pushObject(Equipment.create(newEquipment));

                this.set('model.equipment.equipmentTypeId', 1);
                this.set('model.equipment.quantity', "");

                flashMessages.success("Oprema dodana.");
                this.set('showNewEquipment', false);
            }.bind(this), function() {
                flashMessages.danger("Greška pri dodavanju opreme.");
                this.set('showNewEquipment', false);
            }.bind(this));
		},

		delete(equipment) {
			const flashMessages = Ember.get(this, 'flashMessages');
            this.get('classroomEquipmentService').delete(equipment.id).then(function() {
                this.get('model.classroom.equipment').removeObject(equipment);
            }.bind(this), function(data) {
                flashMessages.danger("Greška pri brisanju opreme.");
            }.bind(this));
		},

        selectType(type) {
            this.set('model.equipment.equipmentTypeId', type);
        },

		toggleNewEquipment() {
            this.toggleProperty('showNewEquipment');
		},
	}
});
