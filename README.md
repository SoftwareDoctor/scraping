# Job Scraping

## Descrizione del Progetto

Il progetto **Job Scraping** è una web application che consente di effettuare lo scraping delle offerte di lavoro presenti su LinkedIn. Gli utenti possono salvare un link relativo ad una specifica offerta di lavoro e avviare lo scraping, il quale estrae il titolo del lavoro (job title) e le tecnologie menzionate nella descrizione dell'offerta. Le informazioni estratte possono essere gestite tramite un set di API CRUD (Create, Read, Update, Delete) e una funzione di ricerca per nome del job title.

## Tecnologie Utilizzate

- **Java Spring Boot**
- **Maven**
- **Jsoup**
- **Docker**
- **PostgreSQL**
- **Angular**

## Funzionalità

- **Salvataggio di link**: Gli utenti possono inserire un link di un'offerta di lavoro presente su LinkedIn.
- **Avvio dello scraping**: Dopo aver salvato il link, viene avviato lo scraping per estrarre il job title e le tecnologie dalla descrizione dell'offerta.
- **Gestione delle informazioni estratte**: Attraverso le API CRUD, gli utenti possono creare, leggere, aggiornare ed eliminare le informazioni relative ai job title e alle tecnologie estratte.
- **Ricerca per Job Title**: Gli utenti possono effettuare ricerche per nome di job title per trovare specifiche offerte di lavoro.


## Come Eseguire il Progetto

1. **Clonazione del Repository**
   ```bash
   git clone https://github.com/tuo-username/job-scraping.git

2. Configurazione del Database PostgreSQL

   Creare un database PostgreSQL e aggiornare le credenziali nel file application.properties.
   Costruzione ed Esecuzione del Backend
  ```bash
   mvn clean install
   mvn spring-boot:run
 ```
3. Esecuzione del Frontend

   Navigare nella directory frontend e installare le dipendenze:
```bash
cd frontend
npm install
```
4.Avviare il server Angular
 ```bash
ng serve
```
### Progetto

`[![Guarda il video "Job Scraping"](https://img.youtube.com/vi/fHZnOEc4r10/maxresdefault.jpg)](https://youtu.be/fHZnOEc4r10)` 

## Autore
Progetto sviluppato da Andrea Italiano
