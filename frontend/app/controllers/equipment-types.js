import Ember from 'ember';
import Equipment from '../models/equipment';
import {
    validator,
    buildValidations
}
from 'ember-cp-validations';

var Validations = buildValidations({
    "model.equipment.name": [
		validator('presence', {
            presence: true
        })
    ],
    "model.equipment.label": [
    	 validator('presence', {
            presence: true
        })
    ]
}, {
    debounce: 500
});

export default Ember.Controller.extend(Validations, {
    showNewType: false,
    equipmentTypeService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

	actions: {
		add() {
			const flashMessages = Ember.get(this, 'flashMessages');
            this.get('equipmentTypeService').create(this.get('model.equipment')).then(function(newType) {
                this.get('model.types').pushObject(Equipment.create(newType));

                this.set('model.equipment', Equipment.create({ label: "", name: "" }));
                flashMessages.success("Tip opreme dodan.");
                this.set('showNewType', false);
            }.bind(this), function() {
                flashMessages.danger("Greška pri dodavanju tipa opreme.");
                this.set('showNewType', false);
            }.bind(this));
		},

		delete(equipment) {
            if (window.confirm("Želite li izbrisati ovaj tip opreme?")) {
			const flashMessages = Ember.get(this, 'flashMessages');
            this.get('equipmentTypeService').delete(equipment.id).then(function() {
                this.get('model.types').removeObject(equipment);
                flashMessages.success("Tip opreme izbrisan.");
            }.bind(this), function(data) {
                flashMessages.danger("Greška pri brisanju tipa opreme.");
            }.bind(this));
            }
		},

		toggleNewType() {
            this.toggleProperty('showNewType');
		},
	}
});
