import Ember from 'ember';
import BaseService from './base-service';
import Reservation from '../models/reservation';

export default BaseService.extend({
    // admin    
    all() {
        var reservations = [];
        this.ajax({ url: "reservations/all", type: "GET" }).then(function(data) {
            data.forEach(function(reservation) {
                reservations.addObject(Reservation.create(reservation));
            });
        });    
        return reservations;
    },

   findByClassroom(classroomId) {
         var reservations = [];
        this.ajax({ url: `reservations/classroom/${classroomId}`, type: "GET" }).then(function(data) {
            data.forEach(function(reservation) {
                reservations.addObject(Reservation.create(reservation));
            });
        });    
        return reservations;
    },

    findAllToday() {
        var reservations = [];
        this.ajax({ url: "reservations/today", type: "GET" }).then(function(data) {
            data.forEach(function(reservation) {
                reservations.addObject(Reservation.create(reservation));
            });
        });    
        return reservations;
    },

    getById(id) {
        var reservation = Reservation.create({});
        this.ajax({ url: `reservations/${id}`, type: "GET"}).then(function(data) {
            reservation.setProperties(data);
        });        

        return reservation;
    },

    create(data) {
        return this.ajax({ url: `reservations`, type: "POST", data: JSON.stringify(data)})
    },

    delete(reservationId) {
        return this.ajax({ url: `reservations/${reservationId}`, type: "DELETE"});
    },
});
