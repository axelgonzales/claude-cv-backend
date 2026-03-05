package com.axel.cv.config;

import com.axel.cv.model.*;
import com.axel.cv.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProfileRepository profileRepository;
    private final ExperienceRepository experienceRepository;
    private final EducationRepository educationRepository;
    private final SkillGroupRepository skillGroupRepository;

    @Override
    public void run(String... args) {
        if (profileRepository.count() > 0) return;

        // Perfil
        Profile profile = new Profile();
        profile.setName("Axel Gonzales Llerena");
        profile.setTitle("Tech Lead | Full Stack Developer");
        profile.setSummary("Profesional con experiencia en análisis, diseño y desarrollo de sistemas empresariales aplicando diversas metodologías de desarrollo como RUP, Scrum, etc. Ingeniero de Sistemas especializado en identificar problemas con el software existente y la creación de nuevos e innovadores programas, manteniendo el compromiso de trabajar con arreglo a las especificaciones del cliente y del proyecto.");
        profile.setEmail("ac.gonzalesllerena@gmail.com");
        profile.setPhone("960764915");
        profile.setLinkedin("https://www.linkedin.com/in/axel-gonzales-llerena/");
        profile.setGithub("https://github.com/axelgonzales");
        profile.setLocation("Lima, Perú");
        profileRepository.save(profile);

        // Experiencia
        saveExp(0, "Niubiz", "Tech Lead", "MAY 2022", "Actualidad", true,
                "Dirección y supervisión de nuevos proyectos de valor agregado y de migraciones. Coordinación con arquitectura, seguridad, ciberseguridad y gestión de accesos. Diseño de Business Case, gestión de nuevas ideas y proyectos estratégicos.",
                "Java,Spring Boot,Spring WebFlux,Angular,NodeJS,AWS SQS,AWS Lambda,AWS S3,AWS EC2,Elastic Beanstalk,RDS,MySQL,Terraform,Scrum");

        saveExp(1, "Globant BCP", "Tech Leader Java", "JUL 2021", "MAY 2022", false,
                "Diseño de nuevas APIs de negocio. Corrección y mejora de microservicios. Mejora de patrones de desarrollo. Líder de equipo Backend.",
                "Java,Spring Boot,Spring WebFlux,Angular,Docker,Kubernetes,Jenkins,Oracle,Azure,Scrum");

        saveExp(2, "Acid Labs", "Developer Senior", "JUL 2022", "SET 2022", false,
                "Desarrollo de proyecto de mejora del sistema logístico de Ripley. Desarrollo de APIs en NodeJS con Kafka. Desarrollo y diseño de pantallas con Angular.",
                "NodeJS,Kafka,Angular,Scrum");

        saveExp(3, "Prima AFP", "Full Stack Developer Senior", "JUL 2020", "JUN 2021", false,
                "Corrección y mejoras en sistema principal. Corrección y mejora de microservicios. Desarrollo de microservicios Gateway. Migración de servicios a microservicios.",
                "Java,Spring Boot,Spring WebFlux,Angular,ReactJS,Oracle,Docker,Kubernetes,Azure DevOps,NodeJS,Scrum");

        saveExp(4, "Financiera OH!", "Full Stack Developer", "ENE 2020", "JUL 2020", false,
                "Desarrollo de Microservicios core, negocio, gateway y back for frontend. Creación de estándares de unit test. Desarrollo de sistema front Angular.",
                "Java,Spring Boot,Spring Security,Angular,Oracle,Docker,Kubernetes,GCP,Azure DevOps,Rabbit MQ,Scrum");

        saveExp(5, "Rimac Seguros y Reaseguros", "Analista Técnico (Fullstack)", "AGO 2019", "ENE 2020", false,
                "Desarrollo de nuevas funcionalidades. Análisis de requerimientos. Desarrollo de FRONT en Angular. Sistemas de integración y automatización.",
                "Java,Spring Boot,Spring Security,Angular,Oracle,AWS,Azure DevOps,Scrum");

        saveExp(6, "Real Plaza", "Analista Developer", "ENE 2019", "AGO 2019", false,
                "Desarrollo de nuevas funcionalidades. Microservicios (Spring Boot) y Nano-servicios (NodeJS Serverless). Desarrollo de sistema Workflow Manager.",
                "Java,Spring Boot,NodeJS,Angular,AWS,Azure DevOps,Scrum");

        saveExp(7, "INPE", "Fullstack Developer", "NOV 2017", "DIC 2018", false,
                "Migración del sistema de denuncias legacy. Desarrollo de sistema de indicadores, documentario y auditor. Creación de tienda virtual. Desarrollo de aplicaciones móviles.",
                "Java EE,Spring,Angular JS,ReactJS,Ionic,Android,MySQL,Oracle PL/SQL,Jasper Reports");

        saveExp(8, "D&D Ingenieros SAC", "Developer", "DIC 2016", "DIC 2017", false,
                "Soporte de aplicaciones. Desarrollo de aplicaciones Java Web-APP y de escritorio. Desarrollo de manuales de usuario.",
                "Java SE,Java EE,Oracle,jQuery,Tomcat");

        // Educación
        saveEdu(0, "Universidad Nacional del Callao", "Ingeniería de Sistemas", "2015", "2020");
        saveEdu(1, "Universidad Nacional de Ingeniería", "Programador Java", "2015", "2015");
        saveEdu(2, "Universidad Nacional de Ingeniería", "Modelamiento de Datos - Erwin", "2015", "2015");

        // Habilidades
        saveSkill(0, "Backend", "Java 21,Spring Boot,Spring WebFlux,Spring Security,Spring Cloud,Spring Batch,JPA/Hibernate,Maven,Gradle");
        saveSkill(1, "Frontend", "Angular,ReactJS,VueJS,TypeScript,JavaScript,jQuery,HTML5,CSS3");
        saveSkill(2, "Cloud & DevOps", "AWS (SQS,Lambda,S3,EC2,RDS),Azure DevOps,GCP,Docker,Kubernetes,Terraform,Jenkins");
        saveSkill(3, "Bases de Datos", "PostgreSQL,Oracle,MySQL,MongoDB,SQL Server");
        saveSkill(4, "Mensajería & APIs", "Apache Kafka,RabbitMQ,REST,SOAP,SOA,NodeJS");
        saveSkill(5, "Metodologías", "Scrum,RUP,TDD,Microservicios,Arquitectura Hexagonal");
    }

    private void saveExp(int order, String company, String role, String start, String end,
                         boolean current, String desc, String tech) {
        Experience e = new Experience();
        e.setCompany(company);
        e.setRole(role);
        e.setStartDate(start);
        e.setEndDate(end);
        e.setCurrent(current);
        e.setDescription(desc);
        e.setTechnologies(tech);
        e.setDisplayOrder(order);
        experienceRepository.save(e);
    }

    private void saveEdu(int order, String institution, String degree, String start, String end) {
        Education ed = new Education();
        ed.setInstitution(institution);
        ed.setDegree(degree);
        ed.setField("");
        ed.setStartYear(start);
        ed.setEndYear(end);
        ed.setDisplayOrder(order);
        educationRepository.save(ed);
    }

    private void saveSkill(int order, String category, String items) {
        SkillGroup s = new SkillGroup();
        s.setCategory(category);
        s.setItems(items);
        s.setDisplayOrder(order);
        skillGroupRepository.save(s);
    }
}
