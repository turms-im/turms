Cypress.Commands.add('login', () => {
    cy.intercept(Cypress.env('baseUrl') + Cypress.env('turms').apis.admin + '*', {
        statusCode: 200,
        fixture: 'admins'
    });
    const account = 'turms';
    const pwd = 'turms';
    cy.visit('/');
    cy.get('#account').type(account);
    cy.get('#password')
        .type(pwd)
        .type('{enter}');
});