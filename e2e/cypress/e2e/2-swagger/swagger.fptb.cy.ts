describe('Swagger Tests Webservice', () => {
    const baseUrl = Cypress.config('baseUrl')
    const host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
    const port = Cypress.env('port') ? Cypress.env('port') : '8080';

    it('shows swagger', () => {
        const path = `/api/fptb/webjars/swagger-ui/index.html`;
        const baseHost = Cypress.env('host') ? `http://${host}:${port}` : baseUrl;
        cy.visit(`${baseHost}${path}`);
        cy.contains('OpenAPI definition', {timeout: 10000}).should('not.be.null');
        cy.wait(1000);
        cy.get('div[class="servers"] > label > select > option').should('have.value', 'http://localhost:9000/api/fptb')
    });

})