import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { LaboratorySharedModule } from 'app/shared/shared.module';
import { LaboratoryCoreModule } from 'app/core/core.module';
import { LaboratoryAppRoutingModule } from './app-routing.module';
import { LaboratoryHomeModule } from './home/home.module';
import { LaboratoryEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    LaboratorySharedModule,
    LaboratoryCoreModule,
    LaboratoryHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    LaboratoryEntityModule,
    LaboratoryAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class LaboratoryAppModule {}
