import Ember from 'ember';
import BaseService from './base-service';

export default BaseService.extend({

    takeKey(reservationId) {
        return this.ajax({ url: `taken-keys/take`, type: "POST", data: JSON.stringify({reservationId: reservationId})})
    },

    returnKey(reservationId) {
        return this.ajax({ url: `taken-keys/return`, type: "POST", data: JSON.stringify({reservationId: reservationId})})
    },
});
