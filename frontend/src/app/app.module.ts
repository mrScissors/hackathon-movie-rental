import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { MovielistComponent } from './movielist/movielist.component';
import { MovieRestServiceService } from './movie-rest-service.service';
import { LoginComponent } from './login/login.component';
import {FormsModule} from '@angular/forms';
import { RentComponent } from './rent/rent.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { UserService } from './user.service';
import { SharedModule } from './shared/shared/shared.module';
import { HttpClientModule } from '@angular/common/http';
import { MovieDetailComponent } from './movie-detail/movie-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    // MovielistComponent,
    // LoginComponent,
    // RentComponent,
    // MovieDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    FlexLayoutModule,
    SharedModule,
    HttpClientModule
  ],
  providers: [MovieRestServiceService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
