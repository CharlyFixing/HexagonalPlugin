# HexagonalPlugin
This is a plugin for creating all file of the hexagonal architecture

```
├── application/               # Application-specific logic (use cases)
│   ├── inputPort/             # Interfaces for input ports
│   ├── service/               # Service classes that implement use cases
│   └── dto/                   # Data Transfer Objects (DTO)
│       ├── request/           # DTOs for requests
│       └── response/          # DTOs for responses
│
├── domain/                    # Domain entities
│   ├── model/                 # Classes that represent domain models
│   ├── outputPort/            # Interfaces for output ports
│   └── repository/            # Interfaces for repositories
│
└── infrastructure/            # Infrastructure-related code
    ├── dao/                   # Implementations of Data Access Objects (DAO)
    │
    ├── inputAdapter/          # Implementations of input ports
    │   ├── controller/        # REST or GraphQL controllers
    │   ├── validator/         # Classes for input validation
    │   ├── converter/         # Converters from DTOs to domain models
    │   └── feign/             # Feign clients for external service communication
    │
    ├── outputAdapter/         # Implementations of output ports
    │   ├── persistence/       # Implementations of persistence adapters
    │   └── messaging/         # Implementations for messaging (e.g., Kafka consumers)
    │
    └── configuration/         # Application configuration classes
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