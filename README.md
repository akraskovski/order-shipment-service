# order-shipment-service
Event-driven, Axon-based application showing the powerful sides of this architecture built approach.

The basic idea is a order shipment service. You'll be able to create an order, make an invoice 
and ship it to a destination point. This requirements must follow the ACID transaction wrapped.
Architecture was developed using DDD pattern: ***Each instance - is a domain model responsible for only one thing***.

 
So, it's the maven multi-module application, ready to go as a microservices 
communicating with each service by events. The structure Following the CQRS principle contains
* Commands(write)
* Queries + Events(read)  

--- 

# Technologies stack:
* Maven(use wrapper)
* Spring Boot 2.1.9
* Axon Framework v4
* Axon Server v4
* Reactive Cassandra

# Modules description
//todo: TBD

# Build and run
//todo: TBD

# Accessing resources
//todo: TBD