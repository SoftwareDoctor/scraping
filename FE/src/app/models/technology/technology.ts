export class Technology {
    uuid: string;
    name: string;
    jobListingId: number;

    constructor(uuid: string, name: string, jobListingId: number) {
            this.uuid = uuid;
            this.name = name;
            this.jobListingId = jobListingId;
        }
}
