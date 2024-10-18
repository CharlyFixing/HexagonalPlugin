# HexagonalPlugin
This is a plugin for creating all file of the hexagonal architecture

```
└── application/               # Application-specific logic (use cases)
    ├── port/                  # Interfaces for input and output ports
    │   ├── input/             # Input ports (e.g., use cases)
    │   └── output/            # Output ports (communication with infrastructure)
    ├── service/               # Implementations of use cases
    ├── dto/                   # Data Transfer Objects (DTO)
    │   ├── request/           # DTOs for requests
    │   └── response/          # DTOs for responses
│
├── domain/                    # Core domain (entities and business logic)
│   ├── model/                 # Classes representing domain models
│   └── repository/            # Interfaces for repositories
│
└── infrastructure/            # Technical implementations (infrastructure)
    ├── adapter/               # Input and output adapters
    │   ├── input/             # Input adapters (REST controllers, etc.)
    │   │   ├── controller/    # REST or GraphQL controllers that handle incoming requests
    │   │   ├── validator/     # Classes for validating input data from requests
    │   │   ├── converter/     # Converters from DTOs to domain models
    │   │   └── messaging/      # Messaging-related input adapters
    │   │       └── consumer/   # Classes for consuming messages from messaging systems
    │   │
    │   └── output/            # Output adapters
    │       ├── dao/          # Implementations of Data Access Objects (DAO) for interacting with data sources
    │       ├── persistence/   # Implementations for persistence (databases)
    │       ├── messaging/     # Messaging-related output adapters
    │       │   └── producer/  # Classes for producing messages to messaging systems
    │       └── feign/         # Feign clients for communication with external services
    │
    └── configuration/         # Configuration classes (e.g., Feign configuration)

    
```
## Deployment
To deploy this project run
```
  IntelliJ IDEA 2023.2.8 
  Gradel 8.0
  Java 17
```

## Authors

- [@CharlyFixing](https://github.com/CharlyFixing)