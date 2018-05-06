import Ember from 'ember';
import BaseValidator from 'ember-cp-validations/validators/base';

const UniqueClassroomName = BaseValidator.extend({
    classroomService: Ember.inject.service(),

    validate(value) {
        if(value === "") {
          return true;
        }
        
        return this.get('classroomService').nameExists(value).then(function() {
            return true;
        }, function() {
            return "Sala sa ovakvim nazivom već postoji.";
        });
    }
});

UniqueClassroomName.reopenClass({
    getDependentsFor( /* attribute, options */ ) {
        return [];
    }
});

export default UniqueClassroomName;