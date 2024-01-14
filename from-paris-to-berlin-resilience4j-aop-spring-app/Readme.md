# From Paris to Berlin Resilience4J AOP Demo

## Example request

```shell
curl -X POST http://localhost:8080/api/fptb/blockage -H "Content-Type: application/json" --data '{"id":1,"name":"Paris","forward":[{"id":2,"name":"Soissons","forward":[{"id":5,"name":"Aken","forward":[{"id":8,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":6,"name":"Heerlen","forward":[{"id":8,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":7,"name":"Düren","forward":[{"id":8,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":3,"name":"Compiègne","forward":[{"id":5,"name":"Aken","forward":[{"id":8,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":6,"name":"Heerlen","forward":[{"id":8,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":7,"name":"Düren","forward":[{"id":8,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":4,"name":"Reims","forward":[{"id":5,"name":"Aken","forward":[{"id":8,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":6,"name":"Heerlen","forward":[{"id":8,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":7,"name":"Düren","forward":[{"id":8,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]}'
```

```shell
curl -X POST http://localhost:8080/api/fptb/blockage -H "Content-Type: application/json" --data '{"id":1,"name":"Paris","forward":[{"id":2,"name":"Soissons","forward":[{"id":5,"name":"Aken","forward":[{"id":8,"name":"Wolfsburg","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":9,"name":"Braunschweig","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":6,"name":"Heerlen","forward":[{"id":8,"name":"Wolfsburg","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":9,"name":"Braunschweig","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":7,"name":"Düren","forward":[{"id":8,"name":"Wolfsburg","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":9,"name":"Braunschweig","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":3,"name":"Compiègne","forward":[{"id":5,"name":"Aken","forward":[{"id":8,"name":"Wolfsburg","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":9,"name":"Braunschweig","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":6,"name":"Heerlen","forward":[{"id":8,"name":"Wolfsburg","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":9,"name":"Braunschweig","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":7,"name":"Düren","forward":[{"id":8,"name":"Wolfsburg","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":9,"name":"Braunschweig","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":4,"name":"Reims","forward":[{"id":5,"name":"Aken","forward":[{"id":8,"name":"Wolfsburg","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":9,"name":"Braunschweig","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":6,"name":"Heerlen","forward":[{"id":8,"name":"Wolfsburg","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":9,"name":"Braunschweig","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":7,"name":"Düren","forward":[{"id":8,"name":"Wolfsburg","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]},{"id":9,"name":"Braunschweig","forward":[{"id":10,"name":"Berlin","forward":[],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]}],"blockageTimeTable":[]}'
```

```shell
curl -X POST http://localhost:8080/api/fptb/blockage -H "Content-Type: application/json" --data '{}'
```

```shell
curl -X POST http://localhost:8080/api/fptb/blockage -H "Content-Type: application/json" --data '{"id":8,"name":"Berlin","forward":[],"blockageTimeTable":[]}'
```

## Payload Example

```json
{
  "id": 1,
  "name": "Paris",
  "forward": [
    {
      "id": 2,
      "name": "Soissons",
      "forward": [
        {
          "id": 5,
          "name": "Aken",
          "forward": [
            {
              "id": 8,
              "name": "Berlin",
              "forward": [],
              "blockageType": null
            }
          ],
          "blockageType": null
        },
        {
          "id": 6,
          "name": "Heerlen",
          "forward": [
            {
              "id": 8,
              "name": "Berlin",
              "forward": [],
              "blockageType": null
            }
          ],
          "blockageType": null
        },
        {
          "id": 7,
          "name": "Düren",
          "forward": [
            {
              "id": 8,
              "name": "Berlin",
              "forward": [],
              "blockageType": null
            }
          ],
          "blockageType": null
        }
      ],
      "blockageType": null
    },
    {
      "id": 3,
      "name": "Compiègne",
      "forward": [
        {
          "id": 5,
          "name": "Aken",
          "forward": [
            {
              "id": 8,
              "name": "Berlin",
              "forward": [],
              "blockageType": null
            }
          ],
          "blockageType": null
        },
        {
          "id": 6,
          "name": "Heerlen",
          "forward": [
            {
              "id": 8,
              "name": "Berlin",
              "forward": [],
              "blockageType": null
            }
          ],
          "blockageType": null
        },
        {
          "id": 7,
          "name": "Düren",
          "forward": [
            {
              "id": 8,
              "name": "Berlin",
              "forward": [],
              "blockageType": null
            }
          ],
          "blockageType": null
        }
      ],
      "blockageType": null
    },
    {
      "id": 4,
      "name": "Reims",
      "forward": [
        {
          "id": 5,
          "name": "Aken",
          "forward": [
            {
              "id": 8,
              "name": "Berlin",
              "forward": [],
              "blockageType": null
            }
          ],
          "blockageType": null
        },
        {
          "id": 6,
          "name": "Heerlen",
          "forward": [
            {
              "id": 8,
              "name": "Berlin",
              "forward": [],
              "blockageType": null
            }
          ],
          "blockageType": null
        },
        {
          "id": 7,
          "name": "Düren",
          "forward": [
            {
              "id": 8,
              "name": "Berlin",
              "forward": [],
              "blockageType": null
            }
          ],
          "blockageType": null
        }
      ],
      "blockageType": null
    }
  ],
  "blockageType": null
}
```

## References

-   [How to send message to client through websocket using Spring](https://stackoverflow.com/questions/28250719/how-to-send-message-to-client-through-websocket-using-spring)
-   [Kotlin's coroutines on Android](https://developer.android.com/kotlin/coroutines)
-   [kotlinx-coroutines-reactor](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-reactor/index.html)

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
