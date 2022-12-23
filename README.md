# Real Estate App (Backend Design)

# Explanation of the classes stucture
This section will provide a detailed breakdown of each folder and explain the structure of the classes contained within each folder. It will cover the purpose and functions of each class, as well as any relationships or dependencies with other classes.

## Controller folder
The PropertyController class is located within this folder and is responsible for handling incoming HTTP requests and returning the appropriate response. This class includes methods, known as "actions," that correspond to different HTTP verbs (e.g., GET, POST, PUT, PATCH, DELETE) and are used to handle requests for specific resources or operations. These actions are defined by their respective endpoints, which specify the location of the resource or operation being requested. The PropertyController class is used to provide a logical interface between the client and the server, allowing the client to request specific resources or operations and receive a response in a standardized format.

## Entity folder
The folder contains two classes, the Seller and Property classes. Both classes are marked with the @Entity annotation, which indicates that they are entities. The Seller class has a set of properties and a constructor, and the Property class has a similar set of properties, including a field called "seller" of type Seller that references a Seller object also the seller field inside the Property class is marked with the @ManyToOne annotation, which indicates that many properties can be associated with a single Seller. The Property class also has a constructor. Both the Seller and Property classes are used to store and manipulate data within the application.

## Exception folder
The folder contains two classes, "KeyDoesNotMatch" and "RessourceNotFound," which both extend the RuntimeException class. The KeyDoesNotMatch exception is thrown when someone tries to update or delete a property using a key that does not match the actual key associated with the property's seller. This exception returns a 401 status code, indicating that the access is unauthorized. The RessourceNotFound exception is thrown when the ID of the property cannot be found in the database. This exception returns a 404 status code, indicating that the requested resource was not found. These exceptions are used to handle errors and provide appropriate responses to the client in the event of unauthorized access or missing data.

## Repository folder
The folder contains two interfaces, "PropertyRepository" and "SellerRepository," which both extend from the CrudRepository interface. When extending this interface, the type of the entity and the type of the primary key must be specified. These interfaces are responsible for managing the persistence of data and the interactions with the database. They provide a set of methods for performing CRUD operations on the data, such as creating, reading, updating, and deleting records. The PropertyRepository and SellerRepository interfaces will be used by the service class, which is located in the Service folder, to perform data access tasks.

## Request folder
In this folder, four classes can be found. The first one is the PropertyRequest class, which contains the necessary fields for adding a property to the database. The next class is the SellerRequest class, which is used inside the PropertyRequest class when adding a seller to the database. The third class in this folder is the UpdatePropertyRequest, which is used when a client makes a request to update the status of a property. The last class is the DeletePropertyRequest, which follows a similar pattern to the UpdatePropertyRequest but only requires the ID and seller key fields and does not include a status field. This class is used to delete a property using the provided ID and seller key. These four classes are used to represent the data that is being requested by the client and passed to the server for processing and persistence. They provide a structured way to send and receive data and can be used to validate the input and ensure that it meets the requirements of the application.

## Response folder
The response folder contains classes that are used to represent the data that will be sent back to the client as part of the API's response. Inside this folder, there are two classes: PropertyResponse and SellerResponse. The SellerResponse class is used as a field type inside the PropertyResponse class, which means that it is a property of the PropertyResponse class. The PropertyResponse class will be used when the program has completed an operation such as adding or updating a property, and needs to return the results to the client. These classes are used to define the structure and content of the data that will be sent back to the client, and will be serialized into the JSON format for transmission over the web.

## Service folder
The PropertyService class, located in the service folder, contains logic and methods used by the controller to handle requests. It uses the PropertyRepository and SellerRepository interfaces to access the seller and property tables. The @Autowired annotation is used to automatically create implementations of these interfaces, allowing the PropertyService to perform queries on the seller and property tables.

# Database Design: Entity-Relationship Diagram
The diagram illustrates the relationship between the Seller and Property entities. The relationship between these two entities is a one-to-many (1:M) relationship, which means that a single Seller can sell multiple Properties, but each Property can only be owned by one Seller. This relationship is represented by the foreign key "SELLER_ID" within the Property entity, which refers to the Seller that the Property belongs to.

![Screenshot 2022-12-21 133924](https://user-images.githubusercontent.com/99833243/208979436-a77b80bf-a6f3-49dd-bdad-78214156ec99.png)

# End-points documentation for backend
In my application, all HTTP methods (GET, PUT, PATCH, DELETE) use the same endpoint, which is '/api/properties'. This is because my application does not have users and instead focuses on allowing sellers to post their properties and receive inquiries. As a result, there is no need for individual IDs or other parameters after the '/properties' portion of the endpoint. When a GET, PUT, PATCH, or DELETE request is made, it is accessing the entire collection of properties. This allows sellers to easily manage their listings and interested parties to view and inquire about available properties.

## GET
This HTTP method returns a list of propertyResponse objects that contains all the properties that have been added to the database. The propertyResponse objects do not include the seller's security key. When called, this method will retrieve all the properties from the database and return them in the propertyResponse list.

## PUT
This HTTP method adds a property and a seller to the database. Before doing so, it retrieves the seller key from the sellerRequest object nested within the propertyRequest. It then checks the database to see if the seller already exists based on the seller key. If the seller key matches an existing seller in the database, the method uses the existing seller information. If the seller key does not match any keys in the database, the method creates a new seller. The method then creates a new property object with the seller data from the database and adds the property to the database.

## PATCH
This HTTP method updates the status field of a property, which indicates whether the property is sold or available. The method takes an UpdatePropertyRequest as input, which includes the ID of the property, the seller's security key, and the desired status. To update the property, the method first retrieves the property using the provided ID. If the ID is not found, it throws an error stating that the property is not found. If the ID is found, the method retrieves the seller key from the property and compares it to the key provided in the request. If the keys match, the method creates a new property object with the same information but a different status. If the keys do not match, the method throws a 401 Unauthorized error, stating that the key does not match the ID.

## DELETE
This HTTP method deletes a property from the database, but does not delete the seller associated with the property. It takes a DeletePropertyRequest as input, which includes the ID of the property to be deleted and the security key of the seller that the property belongs to. The method follows the same process as the PATCH request to ensure that only the authorized seller can delete the property. It retrieves the property using the provided ID. If the ID is not found, it throws an error stating that the property is not found. If the ID is found, the method retrieves the seller key from the property and compares it to the key provided in the request. If the keys match, the method deletes the property record from the property table. If the keys do not match, the method throws a 401 Unauthorized error, stating that the key does not match the ID."
