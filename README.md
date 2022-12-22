# Real Estate App (Backend Design)

# Explanation of the classes stucture
This section will provide a detailed breakdown of each folder and explain the structure of the classes contained within each folder. It will cover the purpose and functions of each class, as well as any relationships or dependencies with other classes.

## Controller folder

## Entity folder

## Exception folder

## Repository folder

## Request folder

## Response folder

## Service folder


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
