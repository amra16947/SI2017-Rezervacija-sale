import Ember from 'ember';
import Classroom from '../models/classroom';
import {
    validator,
    buildValidations
}
from 'ember-cp-validations';

var Validations = buildValidations({
    "model.classroom.name": [
        validator('presence', {
            presence: true
        }),
        validator('length', {
            min: 1,
            max: 255
        }),
        validator('unique-classroom-name')
    ]
}, {
    debounce: 500
});

export default Ember.Controller.extend(Validations, {
    classroomService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

    showNewClassroom: false,

    actions: {
        add() {
            const flashMessages = Ember.get(this, 'flashMessages');
            this.get('classroomService').create(this.get('model.classroom')).then(function(newClassroom) {
            this.get('model.classrooms').pushObject(Classroom.create(newClassroom));
            this.set('model.classroom', Classroom.create({name: "", seatCount: 15, keyCount: 4, teacherId: null, status: 0, type: 0}));
            this.toggleProperty('showNewClassroom');
            this.flashMessages('Uspjesno kreirana prostorija!');
          }.bind(this), function() {
            flashMessages.danger("Greška pri dodavanju sale.");
            this.toggleProperty('showNewClassroom');
          }.bind(this));
        },

        delete(user) {
            if (window.confirm("Želite li izbrisati ovu salu?")) {
            const flashMessages = Ember.get(this, 'flashMessages');
            this.get('classroomService').delete(user.id).then(function() {
                this.get('model.classrooms').removeObject(user);
                flashMessages.success("Uspješno obrisana sala.");
            }.bind(this), function(data) {
                flashMessages.danger("Greška pri brisanju sale.");
            }.bind(this));
        }
        },

        toggleNewClassroom() {
            this.toggleProperty('showNewClassroom');
        },

        selectStatus(classroom) {
            this.set('model.classroom.classroom', classroom);
        },

        selectType(type) {
            this.set('model.classroom.type', type);
        },

        selectTeacher(teacher) {
            this.set('model.classroom.teacherId', teacher.id);
        }
    }
});
