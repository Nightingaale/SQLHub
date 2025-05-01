## Technologies 

- **Java(17)**
- **Gradle**
- **Hibernate**
- **PostgreSQL**
- **Docker**
  
## Information for the repository:

1. **Clone the repository**
``` bash  
git clone https://github.com/Nightingaale/SQLHub.git
```

2. **Find directory & open**
``` bash
cd ~/SQLHub
```

3. Configure to your needs hibernate.cfg.xml & .env files

- **hibernate.cfg.xml:**
```
<hibernate-configuration>
  <session-factory>
    <property name="connection.url">jdbc:postgresql://localhost:YOUR-PORT/YOUR-DB-NAME</property>
    <property name="connection.driver_class">org.postgresql.Driver</property>
    <property name="connection.username">YOUR-USERNAME</property>
    <property name="connection.password">YOUR-PASSWORD</property>
    <property name="show_sql">true</property>

  </session-factory>
</hibernate-configuration>
```

- **.env-example**
```
DB_PASSWORD=YOUR-PASSWORD
DB_USER=YOUR-USER
DB_NAME=YOUR-DB-NAME
DB_PORT=YOUR-PORT
```

4. **Build the project**
``` bash
./gradlew build
```

5. Start docker-compose 
``` bash
docker compose up -d
```
