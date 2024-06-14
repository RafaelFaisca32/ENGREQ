import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from '../../../services/api/api.service';
import { createRequestOption } from '../../../shared';
import { Frame } from './frame.model';

@Injectable({ providedIn: 'root' })
export class FrameService {
  private resourceUrl = ApiService.API_URL + '/frames';

  constructor(protected http: HttpClient) {}

  create(frame: Frame): Observable<HttpResponse<Frame>> {
    return this.http.post<Frame>(this.resourceUrl, frame, { observe: 'response' });
  }

  update(frame: Frame): Observable<HttpResponse<Frame>> {
    return this.http.put(`${this.resourceUrl}/${frame.id}`, frame, { observe: 'response' });
  }

  find(id: number): Observable<HttpResponse<Frame>> {
    return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<Frame[]>> {
    const options = createRequestOption(req);
    return this.http.get<Frame[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
