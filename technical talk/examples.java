@cofigurations
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="domain.model")
@Getter @Setter
public class DefaultCoreBuilderConfig {
	
	private Map<String, String> typeClassMappings;
	
	private List<String> basePackages;
	
	@Value("${platform.config.secure.regex}")
	private String secureRegex;
	
	@Bean
	@DependsOn("securityUtils")
	public JustLogit justLogit() {
		return new JustLogit();		
	}
	
	@Bean
	public BeanResolverStrategy defaultBeanResolver(ApplicationContext appCtx) {
		return new DefaultBeanResolverStrategy(appCtx);
	}
	
	@Bean
	public DomainConfigBuilder domainConfigBuilder(EntityConfigBuilder configBuilder){
		return new DomainConfigBuilder(configBuilder, basePackages);
	}
	
//	@Bean
//	public ParamEventAMQPListener paramEventAMQPListener(SimpMessageSendingOperations messageTemplate,CommandTransactionInterceptor interceptor) {
//		return new ParamEventAMQPListener(messageTemplate, interceptor);
//	}
	
	@Bean
	public ParamUpdateEventListener paramEventUpdateListener() {
		return new ParamUpdateEventListener();
	}
	
	@Bean
	public DefaultValidatorProvider defaultValidatorProvider(){
		return new DefaultValidatorProvider();
	}
	
	@Bean
	public EventHandlerConfigFactory eventHandlerConfigFactory(BeanResolverStrategy beanResolver) {
		return new EventHandlerConfigFactory(beanResolver);
	}
	
	@Bean 
	public AnnotationConfigHandler annotationConfigHandler(PropertyResolver propertyResolver) {
		Map<Class<? extends Annotation>, AnnotationAttributeHandler> attributeHandlers = new HashMap<>();
		attributeHandlers.put(Constraint.class, new ConstraintAnnotationAttributeHandler());
		
		return new DefaultAnnotationConfigHandler(new DefaultAnnotationAttributeHandler(), attributeHandlers, propertyResolver);
	}
	
	@Bean
	public EntityConfigBuilder entityConfigBuilder(BeanResolverStrategy beanResolver){
		if(typeClassMappings==null) {
			typeClassMappings = new HashMap<>();
		}
		
		return new DefaultEntityConfigBuilder(beanResolver, typeClassMappings);
	}
	
	@Bean
	public EntityStateBuilder entityStateBuilder(BeanResolverStrategy beanResolver){
		return new DefaultEntityStateBuilder(beanResolver);
	}
	
	@Bean
	public QuadModelBuilder quadModelBuilder(BeanResolverStrategy beanResolver) {
		return new DefaultQuadModelBuilder(beanResolver);
	} 
	
	@Bean
	public SecurityUtils securityUtils() {
		return new SecurityUtils(secureRegex);
	}
