//import { faker } from '@faker-js/faker';
const { faker } = require('@faker-js/faker');
//import { v4 as uuidv4 } from 'uuid';
const uuid = require('uuid')

function generateRandomPerson(id) {
    //faker.PersonModule.firstName()
  const firstName = faker.person.firstName();
  const lastName = faker.person.lastName();
  //const email = faker.internet.email({ firstName, lastName });
  const email = faker.internet.email();
  const username = faker.internet.username();
  
  return {
    id,
    public_id: faker.string.uuid(),
    first_name: firstName,
    last_name: lastName,
    email,
    address_street: faker.location.streetAddress(),
    address_zip_code: faker.location.zipCode(),
    address_city: faker.location.city(),
    address_country: faker.location.country(),
    image_url: faker.image.avatar(),
    last_seen: faker.date.past().toISOString(),
    created_date: faker.date.past().toISOString(),
    last_modified_date: faker.date.recent().toISOString(),
    password: faker.internet.password(),
    username
  };
}

let insertStatements = [];

for (let i = 1; i <= 105; i++) {
  const person = generateRandomPerson(i);
  const insertStatement = `INSERT INTO ecommerce.person (
    public_id, first_name, last_name, email, address_street, address_zip_code, 
    address_city, address_country, image_url, last_seen, created_date, 
    last_modified_date, password, username
  ) VALUES (
    '${person.public_id}', '${person.first_name}', '${person.last_name}', '${person.email}', 
    '${person.address_street}', '${person.address_zip_code}', '${person.address_city}', 
    '${person.address_country}', '${person.image_url}', '${person.last_seen}', 
    '${person.created_date}', '${person.last_modified_date}', '${person.password}', '${person.username}'
  );`;
  
  insertStatements.push(insertStatement);
}
console.log(insertStatements.join('\n'));
console.log(`\nGenerated ${insertStatements.length} INSERT statements.`);