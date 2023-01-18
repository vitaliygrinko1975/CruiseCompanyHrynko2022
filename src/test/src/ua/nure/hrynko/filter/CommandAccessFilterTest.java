package ua.nure.hrynko.filter;


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CommandAccessFilterTest {

    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;

    @Mock
    FilterChain filterChain;

    @Mock
    HttpSession sessionTest;

    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    FilterConfig filterConfig;

    private static final String ADMIN_ALLOWED_COMMANDS = "adminPage listAdmin pageAdminCruises pageAdminOrders " +
            "pageAdminChangeStatusWithWithdrawalFromDeposit adminPageCruiseRemoveCruise adminPageRemoveUser";
    private static final String CLIENT_ALLOWED_COMMANDS = "clientPage AddToBasket goToBasket RemoveOneUnitFromBasket " +
            "AddOneUnitInBasket RemoveOnePositionFromBasket clientBasketConfirmOrderOfSelectedUnits" +
            " cruiseFindByStartDate cruiseFindByDuration ClientPageGoToTopUpYourAccount clientPageGoToUpdatingAccountPage";
    private static final String COMMON_ALLOWED_COMMANDS = "noCommand";
    private static final String OUT_OF_CONTROL_COMMANDS = "login logout welcome signUpPage signUpUser";
    private static final String ADMIN = "admin";
    private static final String CLIENT = "client";
    private static final String COMMON = "common";
    private static final String OUT_OF_CONTROL = "out-of-control";
    private static final String COMMAND = "command";

    @BeforeEach
    public void setUp() {
        httpServletRequest = mock(HttpServletRequest.class);
        httpServletResponse = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
        sessionTest = mock(HttpSession.class);
        requestDispatcher = mock(RequestDispatcher.class);
        filterConfig = mock(FilterConfig.class);

        when(filterConfig.getInitParameter(ADMIN)).thenReturn(ADMIN_ALLOWED_COMMANDS);
        when(filterConfig.getInitParameter(CLIENT)).thenReturn(CLIENT_ALLOWED_COMMANDS);
        when(filterConfig.getInitParameter(COMMON)).thenReturn(COMMON_ALLOWED_COMMANDS);
        when(filterConfig.getInitParameter(OUT_OF_CONTROL)).thenReturn(OUT_OF_CONTROL_COMMANDS);

        when(httpServletRequest.getSession(anyBoolean()))
                .thenReturn(sessionTest);
        when(httpServletRequest.getRequestDispatcher(anyString()))
                .thenReturn(requestDispatcher);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " ", "      "})
    void shouldRedirectToErrorPageIfCommandAttributeIsBlankOrNull(String command) throws Exception {
        //given
        when(httpServletRequest.getParameter(COMMAND))
                .thenReturn(command);

        CommandAccessFilter adminFilter = new CommandAccessFilter();
        adminFilter.init(filterConfig);

        //when
        adminFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        //then
        verify(filterChain, never()).doFilter(httpServletRequest, httpServletResponse);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
    }

    @ParameterizedTest
    @MethodSource("provideStringsAdminAllowedCommands")
    void shouldJumpToJspForAdminIfCommandIsInAdminList(String command) throws Exception {
        //given
        when(httpServletRequest.getParameter(COMMAND))
                .thenReturn(command);

        CommandAccessFilter adminFilter = new CommandAccessFilter();
        adminFilter.init(filterConfig);
        //when
        adminFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
        //then
        verify(requestDispatcher, times(1)).forward(httpServletRequest, httpServletResponse);

    }

    @ParameterizedTest
    @MethodSource("provideStringsClientAllowedCommands")
    void shouldJumpToJspForClientIfCommandIsInClientList(String command) throws Exception {
        //given
        when(httpServletRequest.getParameter(COMMAND))
                .thenReturn(command);

        CommandAccessFilter clientFilter = new CommandAccessFilter();
        clientFilter.init(filterConfig);
        //when
        clientFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
        //then
        verify(requestDispatcher, times(1)).forward(httpServletRequest, httpServletResponse);

    }

    @ParameterizedTest
    @MethodSource("provideStringsCommonAllowedCommands")
    void shouldJumpToJspForAllIfCommandIsInCommonList(String command) throws Exception {
        //given
        when(httpServletRequest.getParameter(COMMAND))
                .thenReturn(command);

        CommandAccessFilter adminFilter = new CommandAccessFilter();
        adminFilter.init(filterConfig);

        //when
        adminFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        //then
        verify(filterChain, never()).doFilter(httpServletRequest, httpServletResponse);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
    }



    @ParameterizedTest
    @MethodSource("provideStringsOutOfControlCommands")
    void shouldProceedToNextFilterIfCommandIsInOutOfControlList(String command) throws Exception {
        //given
        when(httpServletRequest.getParameter(COMMAND))
                .thenReturn(command);

        CommandAccessFilter adminFilter = new CommandAccessFilter();
        adminFilter.init(filterConfig);

        //when
        adminFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        //then
        verify(requestDispatcher, never()).forward(httpServletRequest, httpServletResponse);
        verify(filterChain).doFilter(httpServletRequest, httpServletResponse);
    }

    private static Stream<String> provideStringsAdminAllowedCommands() {
        return Arrays.stream(StringUtils.split(ADMIN_ALLOWED_COMMANDS, StringUtils.SPACE));
    }

    private static Stream<String> provideStringsClientAllowedCommands() {
        return Arrays.stream(StringUtils.split(CLIENT_ALLOWED_COMMANDS, StringUtils.SPACE));
    }

    private static Stream<String> provideStringsCommonAllowedCommands() {
        return Arrays.stream(StringUtils.split(COMMON_ALLOWED_COMMANDS, StringUtils.SPACE));
    }

    private static Stream<String> provideStringsOutOfControlCommands() {
        return Arrays.stream(StringUtils.split(OUT_OF_CONTROL_COMMANDS, StringUtils.SPACE));
    }
}