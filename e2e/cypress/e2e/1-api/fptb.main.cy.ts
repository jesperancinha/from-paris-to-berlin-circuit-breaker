describe('Application tests', () => {
    it('opens main page', () => {
        cy.visit('/')
        cy.fixture('game1').then(function (data) {
            cy.request('POST', '/api/fptb/blockage', data);
        })
        cy.visit('/')
    })
})