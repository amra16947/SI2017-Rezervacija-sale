import Ember from 'ember';
import BaseValidator from 'ember-cp-validations/validators/base';

const UniqueUsername = BaseValidator.extend({
    userService: Ember.inject.service(),

    validate(value) {
        if(value === "") {
          return true;
        }
        
        return this.get('userService').usernameExists(value).then(function() {
            return true;
        }, function() {
            return "Ovaj username je već zauzet.";
        });
    }
});

UniqueUsername.reopenClass({
    getDependentsFor( /* attribute, options */ ) {
        return [];
    }
});

export default UniqueUsername;