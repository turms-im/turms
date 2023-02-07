declare global {
    namespace Cypress {
        interface Chainable {
            login(url: string): void
        }
    }
}

Cypress.Commands.add('login', (url = '/') => {
    cy.intercept(Cypress.env('baseUrl') + Cypress.env('turms').apis.admin + '*', {
        statusCode: 200,
        fixture: 'admins'
    });
    const account = 'turms';
    const pwd = 'turms';
    cy.visit(url);
    cy.get('.login-modal__account').type(account);
    cy.get('.login-modal__password')
        .type(pwd)
        .type('{enter}');
});

export {};