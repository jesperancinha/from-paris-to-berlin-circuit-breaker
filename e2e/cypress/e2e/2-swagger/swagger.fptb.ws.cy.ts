describe('Swagger Tests Web Sockets', () => {
    const baseUrl = Cypress.config('baseUrl')
    const host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
    const port = Cypress.env('port') ? Cypress.env('port') : '8081';
    const path = Cypress.env('port') ? '/api/fptb/broker/ws/swagger-ui/index.html' : '/api/fptb/swagger-ui/index.html';

    it('shows swagger', () => {
        let baseHost = Cypress.env('host') ? `http://${host}:${port}` : baseUrl;
        cy.visit(`${baseHost}${path}`);
        cy.contains('OpenAPI definition', {timeout: 10000}).should('not.be.null');
        cy.wait(1000);
        cy.get('div[class="servers"] > label > select > option').should('have.value', 'http://localhost:9000/api/fptb')
    });
})