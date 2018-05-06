import { moduleFor, test } from 'ember-qunit';

moduleFor('validator:unique-classroom-name', 'Unit | Validator | unique-classroom-name', {
  needs: ['validator:messages']
});

test('it works', function(assert) {
  var validator = this.subject();
  assert.ok(validator);
});
