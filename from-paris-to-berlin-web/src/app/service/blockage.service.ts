import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {handleError} from './error.handler';
import {catchError, retry} from 'rxjs';

const localUrl = '/api/fptb/blockage/moveToCity/';

@Injectable({
  providedIn: 'root'
})
export class BlockageService {

  constructor(private http: HttpClient) {
  }

  moveMyCarTo(id: number) {
    this.http.get(localUrl + id).pipe(
      retry(3), catchError(handleError())).subscribe(() => {
    });
  }
}

