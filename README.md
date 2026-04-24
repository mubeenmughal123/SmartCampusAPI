# SmartCampusAPI
## Client Server Coursework

## Question: In your report, explain the default lifecycle of a JAX-RS Resource class. Is a new instance instantiated for every incoming request, or does the runtime treat it as a singleton? Elaborate on how this architectural decision impacts the way you manage and synchronize your in-memory data structures (maps/lists) to prevent data loss or race conditions.

## JAX-RS resource classes are by default a new instance instantiated for every incoming request which means that every incoming HTTP request creates a new instance instead of singleton. This design allows thread safety as each request as its own instance avoiding a shared state and in this coursework the data is being stored in memory data structures e.g. Array List and HashMap which makes them be shared across all requests which makes them accessible by several threads which can lead to inconsistent or data loss if they are able to be modified at the same time. In the real world developers would instead use thread safe data structures such as Concurrent HashMap to control simultaneous access. However that is not needed in this coursework as it controller and in a single person testing area but the importance of concurrent manage cannot be overlooked.

## Question: Why is the provision of “Hypermedia” (links and navigation within responses) considered a hallmark of advanced RESTful design (HATEOAS)? How does this approach benefit client developers compared to static documentation?

## Hypermedia also known as HATEOAS is an key part of RESTful design which has replies that contain links to related sites granting clients to navigate an API. The result of this has a huge benefit on client developers as they do not need to physically hardcode a URL on the documentation and allows the client to locate different actions once running. This allows the API to advance easier overtime and improve the flexibility and functionally of it.

## Question: When returning a list of rooms, what are the implications of returning only IDs versus returning the full room objects? Consider network bandwidth and client side processing.

## Returning only IDs instead of a list of room makes the response size be less and uses less bandwidth however this method only increases client side complexity as it requires the client to perform extra queries to get more information, if you return the full room objects then it will increase the response but you get all the information in a single request decreasing the amount of request.

## Question: Is the DELETE operation idempotent in your implementation? Provide a detailed justification by describing what happens if a client mistakenly sends the exact same DELETE request for a room multiple times.

## The DELETE operation in this implementation is idempotent which means that if you perform the same operation multiple times the outcome stays the same. The first time a room is deleted it is removed from the system and if the request if processed again the room is no longer existing and an 404 Not Found error will be presented this contents the meaning of idempotency which makes sure that recurring requests don’t cause any issues.

## Question: We explicitly use the @Consumes (MediaType.APPLICATION_JSON) annotation on the POST method. Explain the technical consequences if a client attempts to send data in a different format, such as text/plain or application/xml. How does JAX-RS handle this mismatch?

## The @Consumes (MediaType.APPLICATION_JSON) annotation will only accept a request with a JSON payload, which means that if a client tries to send data in a different format such as text or xml an error message will be displayed as a response of this. The process behind this makes sure there are no problems caused by other formats and assures the API to only process the correctly formatted data.

## Question: You implemented this filtering using @QueryParam. Contrast this with an alternative design where the type is part of the URL path (e.g., /api/v1/sensors/type/CO2). Why is the query parameter approach generally considered superior for filtering and searching collections?

## When searching and filtering it is better to use @QueryParam such as /sensorstype=C02 as it more appropriate and easier than maybe adding the filter in the URL. @QueryParam are designed to distinguish specific filters and allow them to be combined easily allowing them to be more flexible. Instead of adding the entire filters on the actual URL you are more likely to have complex URL’s and at times more confusing if there is an error in there. Overall @QueryParam allows a more sustainable solution for filtering.

## Question: Discuss the architectural benefits of the Sub-Resource Locator pattern. How does delegating logic to separate classes help manage complexity in large APIs compared to defining every nested path in one massive controller class?

## The Sub-Resource Locator pattern enhances the API’s structure by allowing specific classes to manage nested resources. Instead of being handled by the main sensor resource class in the coursework readings of sensors are handled by a sensor reading resource class. This makes the code easier to understand and maintain if needed to be extended, in API’s this method allows there to be more sustainable stopping classes from being too complicated.

## Question: Why is HTTP 422 often considered more semantically accurate than a standard 404 when the issue is a missing reference inside a valid JSON payload?

## The HTTP 422 is considered more semantically accurate compared the standard 404 because it allows there to be more clarification on where exactly the error is and for example if the client sends a sensorID request and there is no sensorID on the system but the server understands the request but is not able to process this request to a semantic errors. This makes 422 a more realistic precise illustration of the issue.

## Question: From a cybersecurity standpoint, explain the risks associated with exposing internal Java stack traces to external API consumers. What specific information could an attacker gather from such a trace?

## Exposing internal java stack traces to API consumers can be a huge risk as it disclosures private information. A stack trace includes details such as class names, file paths and framework info which an attacker can use and then target weaknesses and do targeted attacks. API’s should return common error messages to users and stay far away from giving out any internal information to the outside users.

Question: Why is it advantageous to use JAX-RS filters for cross-cutting concerns like logging, rather than manually inserting Logger.info() statements inside every single resource method?
Using JAX-RS filters for logging is beneficial as it centralizes cross-cutting in one method instead of it being spread across multiple methods. All incoming and outgoing requests are captured by filters making sure the logging remains consistent throughout. This method keeps the resource classes clear and minimalizes code replication. Adding logging statements to each method will make the system more difficult to mange as it goes on and constant repetition of code.
