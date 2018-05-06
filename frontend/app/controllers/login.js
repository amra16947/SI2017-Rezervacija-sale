import Ember from 'ember';

export default Ember.Controller.extend({
    flashMessages: Ember.inject.service(),
    disabled: false,

    authenticate: function(credentials) {
        var authenticator = 'authenticator:jwt';

        return this.get('session').authenticate(authenticator, credentials);
    },

    actions: {
        login: function(credentials, doRedirect) {
            this.set('disabled', true);
            const flashMessages = Ember.get(this, 'flashMessages');
            var self = this;
            this.authenticate(credentials).then(function(value) {
                if (doRedirect) {
                    self.transitionToRoute('index');
                }
                flashMessages.success("Uspješno prijavljen!");
            }.bind(doRedirect), function(reason) {
                flashMessages.clearMessages();
                flashMessages.danger("Pogrešni podaci.");
                this.set('disabled', false);
            }.bind(this));
        },

        loginNormal: function() {
            var credentials = this.getProperties('identification', 'password');
            this.send('login', credentials, true);
        },

        loginWithoutRedirect: function(credentials) {
            this.send('login', credentials, false);
        }
    }
});
