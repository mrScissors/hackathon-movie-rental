export interface IUser {
  _id: string;
  username: string;
  name: string;
  email: string;
  dob: Date;
  rentedMovies: Map<string, Date>;

}
