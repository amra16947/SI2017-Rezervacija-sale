import Ember from 'ember';
import User from '../models/user';
import {
    validator,
    buildValidations
}
from 'ember-cp-validations';

var Validations = buildValidations({
    "model.user.firstName": [
        validator('presence', {
            presence: true
        }),
        validator('length', {
            min: 4,
            max: 255
        })
    ],
    "model.user.lastName": [
        validator('presence', {
            presence: true
        }),
        validator('length', {
            min: 4,
            max: 255
        })
    ],
    "model.user.username": [
        validator('presence', {
            presence: true
        }),
        validator('length', {
            min: 4,
            max: 255
        }),
        validator('unique-username')
    ],
    "model.user.password": [
        validator('presence', {
            presence: true,
        }),
        validator('length', {
            min: 8,
            max: 255
        }),
    ]
}, {
    debounce: 500
});

export default Ember.Controller.extend(Validations, {
    userService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

    showNewUser: false,
    filterTerm: '',

    filterByTerm: function() {
        var term = this.get('filterTerm');
        if (term.length >= 3) {
            this.set('model.users', this.get('userService').filterByTerm(this.get('filterTerm')));
        } else if (term === "") {
            this.set('model.users', this.get('userService').all());
        }
    },

    debounceFilter: function() {
        Ember.run.debounce(this, this.filterByTerm, 400);
    }.observes('filterTerm'),


    actions: {
        add() {
            const flashMessages = Ember.get(this, 'flashMessages');
            this.get('userService').create(this.get('model.user')).then(function(newAccount) {
                this.get('model.users').pushObject(User.create(newAccount));
                this.set('model.user', User.create({ username: "", password: "", firstName: "", lastName: "", type: 0 }));
                flashMessages.success("Korisnik dodan.");
                this.toggleProperty('showNewUser');
            }.bind(this), function() {
                flashMessages.danger("Greška pri dodavanju korisnika.");
                this.toggleProperty('showNewUser');
            }.bind(this));
        },

        delete(user) {
            if (window.confirm("Želite li izbrisati ovog korisnika?")) {
            const flashMessages = Ember.get(this, 'flashMessages');
            this.get('userService').delete(user.id).then(function() {
                this.get('model.users').removeObject(user);
                flashMessages.success("Korisnik izbrisan.");
            }.bind(this), function(data) {
                flashMessages.danger("Greška pri brisanju korisnika.");
            }.bind(this));
            }
        },

        toggleNewUser() {
            this.toggleProperty('showNewUser');
        },

        selectRole(role) {
            this.set('model.user.type', role);
        }
    }
    
    
});
