//import { faker } from '@faker-js/faker';
const { faker } = require('@faker-js/faker');
//import { v4 as uuidv4 } from 'uuid';
const uuid = require('uuid')
//import fs from 'fs/promises';
const fs = require('node:fs/promises');


function generateRandomPerson(id) {
    //faker.PersonModule.firstName()
  const firstName = String(faker.person.firstName().replace(`'`,""));
  const lastName =  String(faker.person.lastName().replace(`'`,""));
  //const email = faker.internet.email({ firstName, lastName });
  const email = faker.internet.email();
  const username = String(faker.internet.username().replace(`'`,""));
  
  return {
    id,
    public_id: faker.string.uuid(),
    first_name: firstName,
    last_name: lastName,
    email,
    address_street: String(faker.location.streetAddress().replace(`'`,"")),
    address_zip_code: faker.location.zipCode(),
    address_city: String(faker.location.city().replace(`'`,"")),
    address_country: String(faker.location.country().replace(`'`,"")),
    image_url: faker.image.avatar(),
    last_seen: faker.date.past().toISOString(),
    created_date: faker.date.past().toISOString(),
    last_modified_date: faker.date.recent().toISOString(),
    password: faker.internet.password(),
    username
  };
}
table=`INSERT INTO ecommerce.person (
  public_id, first_name, last_name, email, address_street, address_zip_code, 
  address_city, address_country, image_url, last_seen, created_date, 
  last_modified_date, password, username
) values `; 
let insertStatements = [];
limite=105
for (let i = 0; i < limite; i++) {
  const person = generateRandomPerson(i);
  let insertStatement;

  if (i===0) {
    insertStatement = `
    ${table}
    (
      '${person.public_id}', '${person.first_name}', '${person.last_name}', '${person.email}', 
      '${person.address_street}', '${person.address_zip_code}', '${person.address_city}', 
      '${person.address_country}', '${person.image_url}', '${person.last_seen}', 
      '${person.created_date}', '${person.last_modified_date}', '${person.password}', '${person.username}'
    ),`;

  } else if (i===limite-1) {
    insertStatement = `
    (
      '${person.public_id}', '${person.first_name}', '${person.last_name}', '${person.email}', 
      '${person.address_street}', '${person.address_zip_code}', '${person.address_city}', 
      '${person.address_country}', '${person.image_url}', '${person.last_seen}', 
      '${person.created_date}', '${person.last_modified_date}', '${person.password}', '${person.username}'
    );`;

  }else{
    insertStatement = `
    (
      '${person.public_id}', '${person.first_name}', '${person.last_name}', '${person.email}', 
      '${person.address_street}', '${person.address_zip_code}', '${person.address_city}', 
      '${person.address_country}', '${person.image_url}', '${person.last_seen}', 
      '${person.created_date}', '${person.last_modified_date}', '${person.password}', '${person.username}'
    ),`;
  }
  
  insertStatements.push(insertStatement);
}

/* table=`INSERT INTO ecommerce.person (
  public_id, first_name, last_name, email, address_street, address_zip_code, 
  address_city, address_country, image_url, last_seen, created_date, 
  last_modified_date, password, username
) values `; 
console.log(table) */
//console.log(insertStatements.join('\n'));
//const summary = `\n-- Generated ${insertStatements.length} INSERT statements.`;

const sqlContent = insertStatements.join('\n');

async function fileSql(){
  try {
    await fs.writeFile('insert_statements.sql', sqlContent);
    //console.log(`Successfully generated ${insertStatements.length} INSERT statements and saved them to insert_statements.sql`);
    
  } catch (error) {
    console.error('Error writing to file:', error);
  }
}



fileSql().then(e=>{
  console.log("archivo generado")
})

//como ejecutar el archivo node generateUsersv1.js 