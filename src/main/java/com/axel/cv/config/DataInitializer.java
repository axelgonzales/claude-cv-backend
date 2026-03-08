package com.axel.cv.config;

import com.axel.cv.model.*;
import com.axel.cv.repository.*;
import com.axel.cv.model.LearnModule;
import com.axel.cv.model.LearnLesson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProfileRepository profileRepository;
    private final ExperienceRepository experienceRepository;
    private final EducationRepository educationRepository;
    private final SkillGroupRepository skillGroupRepository;
    private final BlogPostRepository blogPostRepository;
    private final ProjectRepository projectRepository;
    private final LearnModuleRepository learnModuleRepository;
    private final LearnLessonRepository learnLessonRepository;
    private final ObjectMapper objectMapper;

    private Map<String, List<String>> blogContentMap;

    private Map<String, List<String>> loadBlogContent() {
        if (blogContentMap != null) return blogContentMap;
        try {
            InputStream is = new ClassPathResource("data/blog-content.json").getInputStream();
            blogContentMap = objectMapper.readValue(is, new TypeReference<>() {});
        } catch (Exception e) {
            blogContentMap = Collections.emptyMap();
        }
        return blogContentMap;
    }

    private String getContentJson(String slug) {
        List<String> content = loadBlogContent().get(slug);
        if (content == null) return "[]";
        try {
            return objectMapper.writeValueAsString(content);
        } catch (Exception e) {
            return "[]";
        }
    }

    @Override
    public void run(String... args) {
        initBlogPosts();
        initProjects();
        initLearnModules();

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

    private void initBlogPosts() {
        if (blogPostRepository.count() > 0) return;

            saveBlog(0, "que-es-claude-code",
                    "Claude Code: Tu copiloto de desarrollo con IA",
                    "Una introduccion completa a Claude Code, la CLI oficial de Anthropic. Desde instalacion, CLAUDE.md, memoria persistente, hasta flujos avanzados de desarrollo autonomo.",
                    "Architecture", "2026-02-15", "12 min",
                    getContentJson("que-es-claude-code"),
                    "Claude Code,CLI,Anthropic,IA,CLAUDE.md,Memoria",
                    "/images/blog/que-es-claude-code.png");

            saveBlog(1, "mcp-model-context-protocol",
                    "MCP: Model Context Protocol — Guia completa con estrategias de uso",
                    "El protocolo que conecta Claude Code con el mundo externo. Los 9 MCPs que usamos, 5 recomendados para agregar, estrategias de uso, errores comunes, y cuando usar cada uno.",
                    "MCP", "2026-02-20", "15 min",
                    getContentJson("mcp-model-context-protocol"),
                    "MCP,GitHub,Supabase,Railway,Puppeteer,nano-banana,Shadcn,Tavily,Context7",
                    "/images/blog/mcp-servers-guia.png");

            saveBlog(2, "skills-sistema-habilidades",
                    "Skills: El sistema de habilidades de Claude Code",
                    "Skills son modulos de conocimiento que Claude Code carga bajo demanda. Aprende como funcionan, como crearlos, y los 30+ skills disponibles en nuestro proyecto.",
                    "Skills", "2026-02-22", "9 min",
                    getContentJson("skills-sistema-habilidades"),
                    "Skills,Modular,Reusable,Deploy,Frontend,favicon,docs,pdf",
                    "/images/blog/skills-claude-code.png");

            saveBlog(3, "agents-subagentes-especializados",
                    "Agents y Sub-agentes: Equipos especializados de IA",
                    "Como crear y orquestar agentes especializados que trabajan en paralelo. Desde exploradores de codebase hasta especialistas en Java y Swagger.",
                    "Agents", "2026-02-24", "10 min",
                    getContentJson("agents-subagentes-especializados"),
                    "Agents,Subagent,Explore,java21-spring-refactor,code-reviewer,swagger-expert",
                    "/images/blog/agents-especializados.png");

            saveBlog(4, "commands-slash-personalizados",
                    "Slash Commands personalizados: Tu CLI dentro de Claude Code",
                    "Los slash commands son atajos poderosos. Aprende a crear /java-hex-architect, /swagger, /hu-generator y mas comandos personalizados.",
                    "Commands", "2026-02-26", "8 min",
                    getContentJson("commands-slash-personalizados"),
                    "Commands,Slash,Hexagonal,Swagger,HU Generator,migrate,test-generator",
                    "/images/blog/commands-slash.png");

            saveBlog(5, "hooks-automatizacion-eventos",
                    "Hooks: Automatizacion basada en eventos",
                    "Los Hooks interceptan acciones de Claude Code antes o despues de ejecutarse. Aprende a crear validaciones, automatizaciones y controles de calidad.",
                    "Hooks", "2026-02-28", "7 min",
                    getContentJson("hooks-automatizacion-eventos"),
                    "Hooks,PreToolUse,PostToolUse,Stop,Validation,Automation",
                    "/images/blog/hooks-claude-code.png");

            saveBlog(6, "claude-md-configuracion-proyecto",
                    "CLAUDE.md: La configuracion maestra del proyecto",
                    "CLAUDE.md es el archivo que define como Claude Code interactua con tu proyecto. Aprende a estructurarlo, que incluir, y como usarlo para maximizar la productividad.",
                    "Config", "2026-03-01", "8 min",
                    getContentJson("claude-md-configuracion-proyecto"),
                    "CLAUDE.md,Settings,Conventions,Architecture,Commands,Memory",
                    "/images/blog/configuracion-avanzada.png");

            saveBlog(7, "deploy-produccion-railway-vercel",
                    "Deploy a produccion: Railway + Vercel + Firebase",
                    "Guia completa para desplegar una aplicacion full-stack. Backend en Railway con PostgreSQL, frontend en Vercel, autenticacion con Firebase.",
                    "Deploy", "2026-03-03", "12 min",
                    getContentJson("deploy-produccion-railway-vercel"),
                    "Railway,Vercel,Docker,PostgreSQL,CORS,Firebase,Deploy,Production",
                    "/images/blog/deploy-fullstack.png");

            saveBlog(8, "estrategias-ahorro-tokens",
                    "Estrategias de ahorro de tokens en Claude Code",
                    "Aprende a optimizar el uso de tokens con subagentes, compresion de contexto, agentes en background, y buenas practicas para reducir costos.",
                    "Tokens", "2026-03-05", "9 min",
                    getContentJson("estrategias-ahorro-tokens"),
                    "Tokens,Costos,Optimizacion,Context Window,Subagents,Background",
                    "/images/blog/tokens-optimizacion.png");

            saveBlog(9, "comandos-basicos-claude-code",
                    "Comandos Basicos de Claude Code: /init, /clear, resume, /compact, voice y headless",
                    "Guia exhaustiva de los comandos fundamentales de Claude Code con flujos de trabajo reales. Desde inicializar proyectos con /init hasta programar con voz, controlar Claude remotamente con headless mode, y dominar el ciclo completo de gestion de contexto.",
                    "Commands", "2026-03-06", "22 min",
                    getContentJson("comandos-basicos-claude-code"),
                    "Claude Code,CLI,init,clear,compact,resume,Voice Mode,Headless,Flujos de Trabajo",
                    "/images/blog/comandos-basicos-claude-code.png");

            saveBlog(10, "doctor-configuracion-avanzada",
                    "/doctor, /config y Configuracion Avanzada: Permisos, Hooks, Keybindings y Memoria",
                    "Todo sobre diagnosticar problemas con /doctor, personalizar la experiencia con /config, configurar permisos granulares, hooks de automatizacion, atajos de teclado, y el sistema de memoria persistente que hace a Claude Code recordar entre sesiones.",
                    "Config", "2026-03-06", "18 min",
                    getContentJson("doctor-configuracion-avanzada"),
                    "doctor,config,Permisos,Hooks,Keybindings,Memoria,settings.json,Diagnostico",
                    "/images/blog/doctor-configuracion-avanzada.png");
    }

    private void initProjects() {
        if (projectRepository.count() > 0) return;

            saveProject(0, "cv-fullstack",
                    "CV Web App — Full Stack",
                    "Aplicacion web full-stack para CV profesional con panel de administracion protegido por Firebase.",
                    "CV profesional construido como aplicacion full-stack con Spring Boot 3, React, PostgreSQL y Firebase Authentication. Incluye panel admin protegido, API REST completa y deploy automatizado en Railway y Vercel.",
                    "Java 21,Spring Boot 3,React,TypeScript,Tailwind CSS,PostgreSQL,Firebase Auth,Railway,Vercel",
                    "Full-Stack", "production",
                    "https://axelgonzales.vercel.app",
                    "https://github.com/axelgonzales",
                    "API REST con Spring Boot 3 y Java 21|||Frontend React con TypeScript y Tailwind|||Autenticacion Firebase con roles admin|||Deploy en Railway (backend) y Vercel (frontend)|||PostgreSQL como base de datos principal",
                    "/images/projects/cv-fullstack.png");

            saveProject(1, "claude-code-blog",
                    "Blog — Claude Code Learnings",
                    "Blog tecnico documentando aprendizajes y buenas practicas con Claude Code.",
                    "Blog integrado en la aplicacion CV que documenta todo lo aprendido usando Claude Code como herramienta de desarrollo. Cubre MCP Servers, Skills, Agents, Commands, Hooks y mas.",
                    "React,TypeScript,Tailwind CSS,Spring Boot,PostgreSQL",
                    "Frontend", "production",
                    "https://axelgonzales.vercel.app/blog",
                    null,
                    "9 articulos tecnicos sobre Claude Code|||Navegacion por categorias|||Contenido dinamico desde API|||Diseno responsive con Tailwind",
                    "/images/projects/claude-code-blog.png");

            saveProject(2, "mcp-integrations",
                    "MCP Server Integrations",
                    "Integraciones con MCP Servers para conectar Claude Code con servicios externos.",
                    "Coleccion de integraciones MCP configuradas para conectar Claude Code con GitHub, Supabase, Railway, Puppeteer, Shadcn y mas. Permite gestionar repositorios, bases de datos, deploys y UI desde el CLI.",
                    "MCP Protocol,GitHub API,Supabase,Railway,Puppeteer,Node.js",
                    "DevOps", "completed",
                    null,
                    "https://github.com/axelgonzales",
                    "Integracion con GitHub para gestion de repos y PRs|||Supabase para base de datos y auth|||Railway para deploy y gestion de servicios|||Puppeteer para testing y screenshots",
                    "/images/projects/mcp-integrations.png");

            saveProject(3, "claude-toolkit",
                    "Claude Code Toolkit",
                    "Toolkit de comandos, agentes y skills personalizados para Claude Code.",
                    "Conjunto de herramientas personalizadas para Claude Code incluyendo slash commands para arquitectura hexagonal, agentes especializados para refactoring y code review, y skills reutilizables para deploy y frontend.",
                    "Claude Code,Markdown,YAML,Shell,Java,TypeScript",
                    "AI", "development",
                    null,
                    null,
                    "Slash commands para arquitectura hexagonal y Swagger|||Agentes especializados para refactoring y review|||Skills modulares para deploy y frontend|||Generador de user stories con criterios de aceptacion",
                    "/images/projects/claude-toolkit.png");

            saveProject(4, "magic-board",
                    "Magic Board — Whiteboard Colaborativo",
                    "Aplicacion de pizarra colaborativa en tiempo real para equipos remotos.",
                    "Whiteboard colaborativo en tiempo real que permite a equipos trabajar juntos de forma visual. Incluye herramientas de dibujo, notas adhesivas, y sincronizacion en tiempo real.",
                    "React,TypeScript,Canvas API,WebSockets,Tailwind CSS,Vercel",
                    "Frontend", "production",
                    "https://magic-board-app.vercel.app",
                    null,
                    "Pizarra interactiva con Canvas API|||Colaboracion en tiempo real con WebSockets|||Herramientas de dibujo y notas adhesivas|||Deploy en Vercel con dominio personalizado",
                    "/images/projects/magic-board.png");

            saveProject(5, "niubiz-qr-internacional",
                    "Niubiz QR Internacional — Pagos Cross-Border",
                    "Sistema de pagos QR internacionales para transacciones cross-border en Niubiz.",
                    "Proyecto de pagos QR internacionales que permite transacciones cross-border entre diferentes paises. Desarrollado como Tech Lead en Niubiz, integrando multiples pasarelas de pago y cumpliendo regulaciones internacionales.",
                    "Java,Spring Boot,Spring WebFlux,AWS SQS,AWS Lambda,MySQL,Terraform,Docker",
                    "Backend", "production",
                    null,
                    null,
                    "Arquitectura reactiva con Spring WebFlux|||Integracion con multiples pasarelas de pago internacionales|||Mensajeria asincrona con AWS SQS|||Infraestructura como codigo con Terraform|||Cumplimiento de regulaciones cross-border",
                    "/images/projects/niubiz-qr-internacional.png");
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

    private void saveBlog(int order, String slug, String title, String excerpt,
                          String category, String date, String readTime,
                          String content, String tags, String imageUrl) {
        BlogPost b = new BlogPost();
        b.setSlug(slug);
        b.setTitle(title);
        b.setExcerpt(excerpt);
        b.setCategory(category);
        b.setDate(date);
        b.setReadTime(readTime);
        b.setContent(content);
        b.setTags(tags);
        b.setImageUrl(imageUrl);
        b.setDisplayOrder(order);
        blogPostRepository.save(b);
    }

    private void saveProject(int order, String slug, String title, String description,
                             String longDescription, String technologies, String category,
                             String status, String liveUrl, String githubUrl,
                             String highlights, String imageUrl) {
        Project p = new Project();
        p.setSlug(slug);
        p.setTitle(title);
        p.setDescription(description);
        p.setLongDescription(longDescription);
        p.setTechnologies(technologies);
        p.setCategory(category);
        p.setStatus(status);
        p.setLiveUrl(liveUrl);
        p.setGithubUrl(githubUrl);
        p.setHighlights(highlights);
        p.setImageUrl(imageUrl);
        p.setDisplayOrder(order);
        projectRepository.save(p);
    }

    private void initLearnModules() {
        if (learnModuleRepository.count() > 0) return;

        LearnModule mod1 = saveLearnModule(1, "fundamentos", "Fundamentos de Claude Code",
                "Que es Claude Code, como instalarlo, y tu primera sesion interactiva.",
                "Terminal");

        LearnModule mod2 = saveLearnModule(2, "modelos", "Modelos de IA: Opus, Sonnet, Haiku",
                "Guia completa sobre los 3 modelos de Claude: cuando usarlos, costos, velocidad y casos de uso.",
                "Zap");

        LearnModule mod3 = saveLearnModule(3, "arquitectura-avanzada", "Arquitectura Avanzada con Claude Code",
                "Patrones avanzados: agentes, skills, commands, hooks, y estrategias de ahorro de tokens.",
                "Building2");

        LearnModule mod4 = saveLearnModule(4, "agents", "Agents y Sub-agentes Especializados",
                "Como crear y orquestar agentes que trabajan en paralelo para resolver tareas complejas.",
                "Bot");

        LearnModule mod5 = saveLearnModule(5, "skills", "Skills: Sistema de Habilidades",
                "Modulos de conocimiento reutilizables que Claude Code carga bajo demanda.",
                "Layers");

        LearnModule mod6 = saveLearnModule(6, "commands", "Slash Commands Personalizados",
                "Crea atajos poderosos como /java-hex-architect, /swagger, /hu-generator.",
                "Zap");

        LearnModule mod7 = saveLearnModule(7, "hooks", "Hooks: Automatizacion basada en Eventos",
                "Intercepta acciones antes o despues de ejecutarse para automatizar validaciones.",
                "Workflow");

        LearnModule mod8 = saveLearnModule(8, "mcp", "MCP: Model Context Protocol",
                "Conecta Claude Code con servicios externos: GitHub, Supabase, Railway, Puppeteer.",
                "Network");

        LearnModule mod9 = saveLearnModule(9, "memoria", "Memoria Persistente",
                "Sistema que recuerda contexto entre sesiones para mantener continuidad en tu flujo de trabajo.",
                "Brain");

        // Add lessons for fundamentos module
        saveLesson(mod1, "que-es-claude-code", "Que es Claude Code",
                "La CLI oficial de Anthropic que convierte tu terminal en un copiloto de desarrollo con IA.",
                "5 min",
                java.util.Arrays.asList(
                        "Claude Code es la interfaz de linea de comandos (CLI) oficial de Anthropic.",
                        "## Que puede hacer",
                        "- Leer y escribir archivos de tu proyecto directamente",
                        "- Ejecutar comandos en tu terminal (npm, git, docker, etc.)",
                        "- Buscar en tu codebase con herramientas optimizadas",
                        "- Conectarse a servicios externos via MCP",
                        "- Recordar contexto entre sesiones con memoria persistente"
                ));

        saveLesson(mod1, "instalacion-y-primera-sesion", "Instalacion y primera sesion",
                "Instala Claude Code y ejecuta tu primera sesion interactiva en menos de 5 minutos.",
                "4 min",
                java.util.Arrays.asList(
                        "## Instalacion",
                        "npm install -g @anthropic-ai/claude-code",
                        "Verifica la instalacion:",
                        "claude --version",
                        "## Tu primera sesion",
                        "Navega a cualquier proyecto y ejecuta: claude",
                        "Claude Code detecta automaticamente el lenguaje del proyecto"
                ));

        // Add lessons for modelos module
        saveLesson(mod2, "modelos-comparacion", "Comparacion de modelos",
                "Analisis detallado de Opus, Sonnet y Haiku: velocidad, costo, casos de uso.",
                "8 min",
                java.util.Arrays.asList(
                        "## Los 3 modelos de Claude",
                        "### Claude Opus 4.6 - El cerebro",
                        "- Velocidad: Lento (15-60s)",
                        "- Razonamiento: Maximo",
                        "- Mejor para: Arquitectura, code review profundo, decisiones complejas",
                        "### Claude Sonnet 4.6 - El equilibrio",
                        "- Velocidad: Rapido (3-15s)",
                        "- Razonamiento: Alto",
                        "- Mejor para: Desarrollo diario, generacion de codigo",
                        "### Claude Haiku 4.5 - El veloz",
                        "- Velocidad: Muy rapido (1-5s)",
                        "- Razonamiento: Bueno",
                        "- Mejor para: Validaciones rapidas, formateo, tareas repetitivas"
                ));

        saveLesson(mod2, "cuando-usar-cada-modelo", "Cuando usar cada modelo",
                "Guia practica para elegir el modelo correcto segun tu tarea.",
                "6 min",
                java.util.Arrays.asList(
                        "## Decision rapida",
                        "### Desarrollo diario → Sonnet (default)",
                        "Escribe codigo, haz cambios, ejecuta tests con Sonnet",
                        "### Analizar arquitectura → Opus",
                        "Decisiones complejas de diseno, code review exhaustivo",
                        "### Validaciones mecanicas → Haiku",
                        "Renombrar variables, formatear datos, linting"
                ));

        // Add lessons for agents module
        saveLesson(mod4, "que-son-agents", "Que son los Agents",
                "Aprende como funcionan los agentes autonomos y cuando usarlos.",
                "7 min",
                java.util.Arrays.asList(
                        "Un agent es una instancia especializada de Claude Code que funciona de forma autonoma.",
                        "## Caracteristicas",
                        "- Contexto separado del principal",
                        "- Puede usar otro modelo",
                        "- Limite de contexto independiente",
                        "- Perfecto para tareas complejas",
                        "## Cuando usar agents",
                        "- Tareas de investigacion profunda",
                        "- Code review exhaustivo",
                        "- Generacion de multiples archivos",
                        "- Procesamiento de datos grandes"
                ));

        saveLesson(mod4, "crear-subagentes", "Crear y Orquestar Sub-agentes",
                "Crea equipos de agentes especializados que trabajan en paralelo.",
                "9 min",
                java.util.Arrays.asList(
                        "## Flujo de creacion",
                        "1. Define el rol del agente",
                        "2. Especifica las herramientas disponibles",
                        "3. Configura el modelo (Opus, Sonnet, Haiku)",
                        "4. Ejecuta el agente",
                        "## Ejemplo de equipo",
                        "- Researcher: Investiga requisitos",
                        "- Architect: Diseña la solucion",
                        "- Developer: Implementa el codigo",
                        "- Tester: Valida la calidad"
                ));
    }

    private LearnModule saveLearnModule(int order, String slug, String title, String description, String icon) {
        LearnModule module = new LearnModule();
        module.setSlug(slug);
        module.setTitle(title);
        module.setDescription(description);
        module.setIcon(icon);
        module.setDisplayOrder(order);
        return learnModuleRepository.save(module);
    }

    private void saveLesson(LearnModule module, String slug, String title, String excerpt, String readTime, java.util.List<String> content) {
        LearnLesson lesson = new LearnLesson();
        lesson.setSlug(slug);
        lesson.setTitle(title);
        lesson.setExcerpt(excerpt);
        lesson.setReadTime(readTime);
        lesson.setContent(content);
        lesson.setModule(module);
        learnLessonRepository.save(lesson);
    }
}
