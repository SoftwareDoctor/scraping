export class JobListing {
  uuid: string;
  title: string;
  jobLink: string;
  technologies: string[]; // Modificato per accettare un array di stringhe

  constructor(
    uuid: string,
    title: string,
    jobLink: string,
    technologies: string[] // Modificato per accettare un array di stringhe
  ) {
    this.uuid = uuid;
    this.title = title;
    this.jobLink = jobLink;
    this.technologies = technologies;
  }
}
