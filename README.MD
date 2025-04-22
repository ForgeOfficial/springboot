# API de Gestion des Notes Étudiantes

## Description
Cette API RESTful permet aux enseignants de gérer les notes des étudiants pour différents cours. L'application offre des fonctionnalités pour enregistrer, mettre à jour, supprimer et consulter les notes des étudiants. Elle permet également de générer des rapports de notes par cours ou par étudiant.

## Installation et Configuration

1. **Clonez le dépôt Git**
   ```bash
   git clone https://github.com/ForgeOfficial/springboot.git
   cd grades-management
   ```

2. **Configurez la base de données**

   Créez une base de données PostgresSQL:
   ```sql
   CREATE DATABASE gradesmanagement;
   CREATE TABLE student (
   id BIGSERIAL PRIMARY KEY,
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   email VARCHAR(255)
   );
   
   CREATE TABLE course (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(255)
   );
   
   CREATE TABLE grade (
   id BIGSERIAL PRIMARY KEY,
   value FLOAT,
   student_id BIGINT NOT NULL,
   course_id BIGINT NOT NULL,
   CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student(id),
   CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES course(id)
   );
   ```

   Modifiez le fichier `src/main/resources/application.properties` selon votre configuration:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/gradesmanagement
   spring.datasource.username=postgres
   spring.datasource.password=votre_mot_de_passe
   ```

3. **Compilez et lancez l'application**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   L'API sera accessible à l'adresse: http://localhost:8080