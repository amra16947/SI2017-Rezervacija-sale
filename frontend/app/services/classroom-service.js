import Ember from 'ember';
import BaseService from './base-service';
import Classroom from '../models/classroom';

export default BaseService.extend({
	nameExists: function(name) {
    	return this.ajax({ url: `classrooms/exists`, type: "POST", data: JSON.stringify({"name": name}) });
    },

    // admin    
    all() {
        var classrooms = [];
        this.ajax({ url: "classrooms/all", type: "GET" }).then(function(data) {
            data.forEach(function(classroom) {
                classrooms.addObject(Classroom.create(classroom));
            });
        });    
        return classrooms;
    },

    filterByTerm(term) {
        var classrooms = [];
        this.ajax({ url: `classrooms/filter/${term}`, type: "GET" }).then(function(data) {
            data.forEach(function(classroom) {
                users.addObject(Classroom.create(classroom));
            });
        });    
        return classrooms;
    },

    getById(id) {
        var classroom = Classroom.create({});
        this.ajax({ url: `classrooms/${id}`, type: "GET"}).then(function(data) {
            classroom.setProperties(data);
        });        

        return classroom;
    },

    edit(userId, data) {
        return this.ajax({ url: `classrooms/${userId}`, type: "PUT", data: JSON.stringify(data) });
    },

    create(data) {
        if(data.get('teacherId') === -1) { data.set('teacherId', null); }
        return this.ajax({ url: `classrooms`, type: "POST", data: JSON.stringify(data)})
    },

    delete(userId) {
        return this.ajax({ url: `classrooms/${userId}`, type: "DELETE"});
    },
});
