/* eslint-env node */

module.exports = function(environment) {
    var ENV = {
        modulePrefix: 'client',
        environment: environment,
        rootURL: '/',
        locationType: 'auto',
        EmberENV: {
            FEATURES: {},
            EXTEND_PROTOTYPES: {
                Date: false
            }
        },

        APP: {}
    };

    if (environment === 'development') {
        ENV.host = 'http://localhost:8080';        
        ENV.apiHost = "http://localhost:8080";
    }

    if (environment === 'test') {
        ENV.locationType = 'none';
        ENV.APP.LOG_ACTIVE_GENERATION = false;
        ENV.APP.LOG_VIEW_LOOKUPS = false;
        ENV.APP.rootElement = '#ember-testing';
    }

    if (environment === 'production') {
        ENV.host = 'https://radiant-oasis-76187.herokuapp.com';        
        ENV.apiHost = "https://radiant-oasis-76187.herokuapp.com/";
    }


    ENV['ember-simple-auth'] = {
        authorizer: 'authorizer:token',
        baseURL: '',
        crossOriginWhitelist: ['http://localhost:8080']
    };

    ENV['ember-simple-auth-token'] = {
        refreshAccessTokens: false,
        authorizer: 'authorizer:token',
        identificationField: 'email',
        serverTokenEndpoint: ''
    };

    ENV['ember-simple-auth'].baseURL = ENV.apiHost;
    ENV['ember-simple-auth-token'].serverTokenEndpoint = `${ENV.apiHost}/login`;

    return ENV;
};
