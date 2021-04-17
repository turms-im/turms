describe('Home page', () => {
    beforeEach(() => cy.visit('/'));

    it('mask should exist', () => {
        cy.get('.ant-modal-mask')
            .should('exist');
    });

    describe('Login modal', () => {

        const account = 'turms';
        const pwd = 'turms';

        it('should focus account by default', () => {
            cy.focused()
                .should('have.id', 'account');
        });

        it('should have default URL value', () => {
            cy.get('#url')
                .should('have.value', 'http://localhost:8510');
        });

        it('account input should accept input', () => {
            cy.get('#account')
                .should('be.empty')
                .type(account)
                .should('have.value', account);
        });

        it('password input should accept input', () => {
            cy.get('#password')
                .should('be.empty')
                .type(pwd)
                .should('have.value', pwd);
        });

        context('Form submission', () => {
            beforeEach(() => {
                cy.get('#account').type(account);
                cy.get('#password').type(pwd);
            });

            it('should show an error message on a failed submission', () => {
                cy.intercept(Cypress.env('baseUrl') + Cypress.env('turms').apis.admin + '*', {
                    statusCode: 401
                });
                cy.get('.ant-message-error', {timeout: 30 * 1000})
                    .should('not.exist');
                cy.get('.login-form__submit')
                    .click();
                cy.get('.ant-message-error')
                    .should('be.visible');
            });

            it('should disappear on a successful submission', () => {
                cy.intercept(Cypress.env('baseUrl') + Cypress.env('turms').apis.admin + '*', {
                    statusCode: 200,
                    fixture: 'admins'
                });
                cy.get('.login-form')
                    .should('be.visible');
                cy.get('#password')
                    .focus()
                    .type('{enter}');
                cy.get('.login-form')
                    .should('not.be.visible');
            });
        });

    });
});