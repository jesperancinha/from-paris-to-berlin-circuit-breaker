import {defineConfig} from "cypress";

export default defineConfig({
    e2e: {
        setupNodeEvents(on, config) {
            // implement node event listeners here
        },
        baseUrl: "http://localhost:9000",
        env: {
            TIMEOUT_CONFIG: {
                timeout: 10000
            }
        },
        includeShadowDom: true,
        video: false,
        screenshotOnRunFailure: false,
        supportFile: `${__dirname}/cypress/support/e2e.ts`,
        specPattern: `${__dirname}/cypress/e2e/**/*.cy.{js,jsx,ts,tsx}`,
    },
});
