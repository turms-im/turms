describe('Content - User Info Pane', () => {
    beforeEach(() => {
        cy.intercept(Cypress.env('baseUrl') + Cypress.env('turms').apis.user + '/page?page=0*', {
            statusCode: 200,
            fixture: 'users'
        });
        cy.login('/content/user');
    });

    describe('Data Table', () => {
        it('should be on page 0 by default', () => {
            cy.get('.ant-pagination-item-1.ant-pagination-item-active')
                .should('exist');
        });

        it('should go to page 1 when clicking page 1 button', () => {
            cy.intercept(Cypress.env('baseUrl') + Cypress.env('turms').apis.user + '/page?page=1*', {
                statusCode: 200,
                fixture: 'users'
            }).as('getUsersInPage');
            cy.get('.ant-pagination-item-2')
                .click();
            cy.wait('@getUsersInPage')
                .then(() => {
                    cy.get('.ant-pagination-item-2.ant-pagination-item-active')
                        .should('exist');
                });
        });
    });
});