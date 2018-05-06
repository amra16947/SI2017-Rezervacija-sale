import Ember from 'ember';
import Access from '../models/access';
import {
    validator,
    buildValidations
}
from 'ember-cp-validations';

var Validations = buildValidations({
    "model.access.userId": [
		 validator('presence', {
            presence: true
        })
    ]
}, {
    debounce: 500
});

export default Ember.Controller.extend(Validations, {
    showNewAccess: false,
    accessService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

	actions: {
		add() {
			const flashMessages = Ember.get(this, 'flashMessages');
            this.get('accessService').create(this.get('model.access')).then(function(newAccess) {
                this.get('model.classroom.accessGrants').pushObject(Access.create(newAccess));
                this.set('model.access', Access.create({ userId: "", classroomId: newAccess.classroom }));
                flashMessages.success("Dodan novi pristup.");
                this.set('showNewAccess', false);
            }.bind(this), function() {
                flashMessages.danger("Greška pri dodavanju novog pristupa.");
                this.set('showNewAccess', false);
            }.bind(this));
		},

		delete(access) {
			const flashMessages = Ember.get(this, 'flashMessages');
            this.get('accessService').delete(access.id).then(function() {
                this.get('model.classroom.accessGrants').removeObject(access);
            }.bind(this), function(data) {
                flashMessages.danger("Greška pri brisanju pristupa.");
            }.bind(this));
		},

        selectUser(userId) {
        	console.log("yooo");
        	console.log(userId);
            this.set('model.access.userId', userId);
        },

		toggleNewAccess() {
            this.toggleProperty('showNewAccess');
		}
	}
});
