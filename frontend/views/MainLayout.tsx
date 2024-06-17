import {AppLayout} from '@hilla/react-components/AppLayout';
import {DrawerToggle} from '@hilla/react-components/DrawerToggle';
import Placeholder from 'Frontend/components/placeholder/Placeholder';
import {useRouteMetadata} from 'Frontend/util/routing';
import {Suspense, useEffect} from 'react';
import {NavLink, Outlet} from 'react-router-dom';
import React from 'react';
import { Tabs } from '@hilla/react-components/Tabs.js';
import { Tab } from '@hilla/react-components/Tab.js';
import { Icon } from '@hilla/react-components/Icon.js';
import { UserEndPoint } from 'Frontend/generated/endpoints';
import css from './MainLayout.module.css';
const h1Style = {
    fontSize: 'var(--lumo-font-size-l)',
    margin: 0,
  };
  const iconStyle: React.CSSProperties = {
    marginInlineEnd: 'var(--lumo-space-m)',
    padding: 'var(--lumo-space-xs)',
    boxSizing: 'border-box',
  };
export default function MainLayout() {

        
    const cur = UserEndPoint.getCurrentUser();
    const currentTitle = useRouteMetadata()?.title ?? 'My App';
  
    return (
        <AppLayout primarySection="drawer">
            <div slot="drawer" className={css.drawer}>

                <header>
                    <h1 className="text-l m-0">My App</h1>
                    <Tabs slot="drawer" orientation="vertical">
                        <nav>
                            <NavLink to="/" >
                            <Icon icon="hilla:dashboard" style={iconStyle} />

                            <span>login</span>
                            <NavLink to="/chatList/">
                            chat
                            </NavLink>
                            </NavLink>
                            <NavLink to="/about">
                            About
                            </NavLink>
                            <NavLink to="/pickLabels">
                            picklabel
                            </NavLink>
                        </nav>
                    </Tabs>
                </header>
            </div>
            <DrawerToggle slot="navbar" aria-label="Menu toggle"></DrawerToggle>
            <h2 slot="navbar" className="text-l m-0">
                {currentTitle}
            </h2>

            <Suspense fallback={<Placeholder/>}>
                <Outlet/>
            </Suspense>
            
        </AppLayout>
    );
    
    
}
