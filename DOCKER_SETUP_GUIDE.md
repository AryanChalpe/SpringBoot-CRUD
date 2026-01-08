# Docker Setup Guide for Spring Boot + Swagger + MongoDB

## Project Structure After Setup
```
demo_project/
├── Dockerfile                      (Build image for Spring Boot app)
├── docker-compose.yml              (Orchestrates app + MongoDB)
├── .dockerignore                   (Files to exclude from Docker build)
├── application-docker.properties   (Docker-specific configuration)
├── pom.xml
├── src/
└── target/
```

## Step-by-Step Process

### Step 1: Ensure Docker Desktop is Running
- Open Docker Desktop application on your Windows system
- Verify it's running by checking the system tray

### Step 2: Build and Start Containers
Navigate to your project directory in PowerShell:
```powershell
cd C:\Users\HP\Downloads\demo_project
```

Run the following command:
```powershell
docker-compose up --build
```

This command:
- Builds the Spring Boot Docker image
- Pulls the MongoDB 7.0 image
- Creates both containers
- Starts both services with proper networking

### Step 3: Access Your Application

**Swagger UI**: http://localhost:8081/swagger-ui.html

**API Docs**: http://localhost:8081/v3/api-docs

**MongoDB**: mongodb://localhost:27017
- Username: admin
- Password: password
- Database: simplecrud

### Step 4: Verify Everything is Running
```powershell
docker-compose ps
```

### Step 5: Stop the Containers
```powershell
docker-compose down
```

If you want to also remove MongoDB data:
```powershell
docker-compose down -v
```

## About Docker Images

### MongoDB Image
- **Image Used**: `mongo:7.0` (Official MongoDB image from Docker Hub)
- **Does NOT need to be built**: Docker automatically pulls and uses it
- **Storage**: Uses Docker volumes (`mongodb_data`) to persist data between restarts

### Spring Boot Image
- **Must be built**: Uses multi-stage build (more efficient)
- **Build Stage**: Compiles your code using Maven
- **Runtime Stage**: Runs the compiled JAR with JRE 21
- **Size**: Significantly smaller than including JDK

## Network Communication
- App connects to MongoDB using hostname `mongodb` (not localhost)
- Docker Compose creates an internal network for container communication
- External access: Use `localhost:PORT` from your Windows machine

## Environment Variables Explained

### MongoDB
- `MONGO_INITDB_ROOT_USERNAME`: admin account username
- `MONGO_INITDB_ROOT_PASSWORD`: admin account password
- `MONGO_INITDB_DATABASE`: initial database name

### Spring Boot
- `SPRING_DATA_MONGODB_URI`: Connection string with credentials
- Uses service health check to wait for MongoDB to be ready

## Troubleshooting

### Ports Already in Use
If ports 8081 or 27017 are already in use, modify docker-compose.yml:
```yaml
ports:
  - "8081:8081"  # Change first number to different port (e.g., "8082:8081")
  - "27017:27017"  # Change first number to different port (e.g., "27018:27017")
```

### MongoDB Connection Issues
Check if MongoDB is healthy:
```powershell
docker-compose ps
```
Wait 10-15 seconds for MongoDB health check to pass before accessing the app.

### View Logs
```powershell
docker-compose logs -f demo-app
docker-compose logs -f mongodb
```

### Rebuild without Cache
```powershell
docker-compose up --build --no-cache
```

## Additional Docker Commands

### Interactive MongoDB Shell
```powershell
docker exec -it demo-mongodb mongosh -u admin -p password
```

### View Container Processes
```powershell
docker-compose ps
```

### Remove All Containers and Images
```powershell
docker-compose down
docker image rm demo-project-demo-app mongo:7.0
```

## Summary: What Each Component Does

| Component | Purpose | Created By |
|-----------|---------|-----------|
| **Dockerfile** | Defines how to build the Spring Boot image | You (created for this project) |
| **docker-compose.yml** | Orchestrates MongoDB + Spring Boot containers | You (created for this project) |
| **MongoDB Image** | Database container (pulled from Docker Hub) | Docker Hub (mongo:7.0) |
| **Spring Boot Image** | Your application container (built locally) | Your Dockerfile |

## Next Steps

1. Ensure Docker Desktop is running
2. Navigate to project directory
3. Run: `docker-compose up --build`
4. Access: http://localhost:8081/swagger-ui.html
5. Start testing your API with Swagger UI!
